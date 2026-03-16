package com.bascode.controller;

import com.bascode.model.entity.SupportConversation;
import com.bascode.model.entity.SupportMessage;
import com.bascode.model.entity.User;
import com.bascode.model.enums.SupportSender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/support")
public class SupportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        EntityManagerFactory emf = getEmf();
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, toLong(session.getAttribute("userId")));
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            SupportConversation conversation = findOrCreateConversation(em, user);
            List<SupportMessage> messages = em.createQuery(
                            "SELECT m FROM SupportMessage m " +
                                    "JOIN FETCH m.senderUser su " +
                                    "WHERE m.conversation.id = :cid " +
                                    "ORDER BY m.createdAt ASC",
                            SupportMessage.class
                    )
                    .setParameter("cid", conversation.getId())
                    .setMaxResults(2000)
                    .getResultList();

            SupportMessage last = messages.isEmpty() ? null : messages.get(messages.size() - 1);

            request.setAttribute("user", user);
            request.setAttribute("conversation", conversation);
            request.setAttribute("messages", messages);
            request.setAttribute("lastMessage", last);
            request.getRequestDispatcher("/WEB-INF/views/support.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String body = trimToNull(request.getParameter("message"));
        Long replyToId = toLong(request.getParameter("replyToMessageId"));
        if (body == null) {
            response.sendRedirect(request.getContextPath() + "/support?err=empty");
            return;
        }

        EntityManagerFactory emf = getEmf();
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, toLong(session.getAttribute("userId")));
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            SupportConversation conversation = findOrCreateConversation(em, user);

            SupportMessage msg = new SupportMessage();
            msg.setConversation(conversation);
            msg.setSenderUser(user);
            msg.setSender(SupportSender.USER);
            msg.setBody(body);
            if (replyToId != null) {
                SupportMessage original = em.find(SupportMessage.class, replyToId);
                if (original != null && original.getConversation() != null
                        && original.getConversation().getId().equals(conversation.getId())) {
                    msg.setReplyToMessageId(replyToId);
                }
            }

            em.getTransaction().begin();
            em.persist(msg);
            conversation.setUpdatedAt(LocalDateTime.now());
            em.merge(conversation);
            em.getTransaction().commit();

            response.sendRedirect(request.getContextPath() + "/support");
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            response.sendRedirect(request.getContextPath() + "/support?err=system");
        } finally {
            em.close();
        }
    }

    private static SupportConversation findOrCreateConversation(EntityManager em, User user) {
        SupportConversation existing = em.createQuery(
                        "SELECT sc FROM SupportConversation sc WHERE sc.user.id = :uid",
                        SupportConversation.class
                )
                .setParameter("uid", user.getId())
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (existing != null) return existing;

        SupportConversation sc = new SupportConversation();
        sc.setUser(user);
        em.getTransaction().begin();
        em.persist(sc);
        em.getTransaction().commit();
        return sc;
    }

    private static String trimToNull(String s) {
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    private static Long toLong(Object v) {
        if (v instanceof Long) return (Long) v;
        if (v instanceof Integer) return ((Integer) v).longValue();
        if (v instanceof String) {
            try {
                return Long.valueOf((String) v);
            } catch (Exception ignored) {
                return null;
            }
        }
        return null;
    }

    private EntityManagerFactory getEmf() {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory not found in ServletContext. Ensure JPAInitializer is registered.");
        }
        return emf;
    }
}
