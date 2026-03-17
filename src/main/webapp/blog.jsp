<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/fragment/head.jsp"%>
<body>

<section class="max-w-5xl mx-auto px-8 pb-24">
  <div class="flex items-center gap-4 mb-8">
    <h2 class="text-xs font-black uppercase tracking-[0.5em] text-gray-400">Latest_Intelligence</h2>
    <div class="h-[1px] flex-grow bg-gray-100"></div>
  </div>

  <div class="grid grid-cols-1 lg:grid-cols-3 gap-10">
    
    <div class="lg:col-span-2 group cursor-pointer">
      <div class="relative overflow-hidden mb-6 aspect-video bg-gray-100">
        <div class="absolute top-0 right-0 w-32 h-full bg-[#50C87820] -skew-x-12 translate-x-16"></div>
        <img src="https://images.unsplash.com/photo-1540910419892-f7ef7173fdd4?auto=format&fit=crop&q=80&w=800" 
             alt="Polling Station" 
             class="w-full h-full object-cover grayscale group-hover:grayscale-0 transition-all duration-500">
        <div class="absolute bottom-4 left-4 bg-[#6A0DAD] text-white px-3 py-1 text-[10px] font-bold uppercase tracking-widest">
          Campaign Focus
        </div>
      </div>
      
      <div class="space-y-3">
        <h3 class="text-2xl font-black text-gray-900 leading-tight group-hover:text-[#6A0DAD] transition-colors italic">
          The Silent Shift: Why Municipal Elections Dictate Your Daily Life
        </h3>
        <p class="text-gray-500 leading-relaxed text-sm">
          While national headlines capture the majority of our collective attention, the true architecture of our daily lives is designed at the local level. From road quality to school curricula, the impact is immediate...
        </p>
        <div class="flex items-center gap-4 pt-2">
          <span class="text-[10px] font-bold text-gray-400 uppercase tracking-widest">March 17, 2026</span>
          <span class="w-1 h-1 bg-[#50C878] rounded-full"></span>
          <span class="text-[10px] font-bold text-[#6A0DAD] uppercase hover:underline">Read Analysis →</span>
        </div>
      </div>
    </div>

    <div class="space-y-8">
      <h4 class="text-[11px] font-bold text-gray-900 uppercase border-l-4 border-[#50C878] pl-3 tracking-widest">Active Bulletins</h4>
      
      <div class="border-b border-gray-100 pb-4 group cursor-pointer">
        <span class="text-[#50C878] text-[9px] font-bold uppercase tracking-widest">Infrastructure</span>
        <h5 class="font-bold text-gray-800 group-hover:text-[#6A0DAD] mt-1">District 4 Transit Proposal: What you need to know before Friday.</h5>
      </div>

      <div class="border-b border-gray-100 pb-4 group cursor-pointer">
        <span class="text-[#6A0DAD] text-[9px] font-bold uppercase tracking-widest">Policy Watch</span>
        <h5 class="font-bold text-gray-800 group-hover:text-[#6A0DAD] mt-1">Digital Literacy Fund gains 5% lead in preliminary polling.</h5>
      </div>

      <div class="border-b border-gray-100 pb-4 group cursor-pointer">
        <span class="text-red-500 text-[9px] font-bold uppercase tracking-widest">Urgent</span>
        <h5 class="font-bold text-gray-800 group-hover:text-[#6A0DAD] mt-1">Voter Registration deadline for Primaries: 48 hours remaining.</h5>
      </div>

      <div class="bg-[#F9FAFB] p-6 border-t-2 border-[#6A0DAD] relative overflow-hidden">
        <div class="absolute -right-4 -bottom-4 opacity-5 transform rotate-12">
            <svg class="w-20 h-20" fill="currentColor" viewBox="0 0 20 20"><path d="M17.414 2.586a2 2 0 00-2.828 0L7 10.172V13h2.828l7.586-7.586a2 2 0 000-2.828z"></path><path fill-rule="evenodd" d="M2 6a2 2 0 012-2h4a1 1 0 010 2H4v10h10v-4a1 1 0 112 0v4a2 2 0 01-2 2H4a2 2 0 01-2-2V6z" clip-rule="evenodd"></path></svg>
        </div>
        <p class="text-xs font-bold text-gray-900 uppercase mb-2 italic">Contribute</p>
        <p class="text-xs text-gray-500 leading-tight mb-4">Have an update from your local precinct? Submit a report for verification.</p>
        <button class="text-[10px] font-black text-[#6A0DAD] uppercase tracking-widest border-b-2 border-[#6A0DAD] pb-0.5">Submit Bulletin</button>
      </div>
    </div>

  </div>
</section>

</body>
</html>