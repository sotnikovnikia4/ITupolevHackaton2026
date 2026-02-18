import React, { useState } from 'react';

const REGULATIONS_URL = "https://storage.yandexcloud.net/storagevideo/%D0%BF%D0%BE%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5_%D0%B1%D0%B5%D0%B7_%D0%BA%D0%B5%D0%B9%D1%81%D0%BE%D0%B2.pdf";

const DEADLINE_DATE = new Date('2026-02-19T15:00:00+03:00').getTime();

interface LandingViewProps {
  onStart: () => void;
}

const LandingView: React.FC<LandingViewProps> = ({ onStart }) => {
  const [showClosedMessage, setShowClosedMessage] = useState(false);

  const handleMainClick = () => {
    if (showClosedMessage) return;

    const now = Date.now();
    if (now >= DEADLINE_DATE) {
      setShowClosedMessage(true);
    } else {
      onStart();
    }
  };

  const handleBackToMain = (e: React.MouseEvent) => {
    e.stopPropagation();
    setShowClosedMessage(false);
  };

  return (
    <div 
      onClick={handleMainClick}
      style={{ WebkitMaskImage: '-webkit-radial-gradient(white, black)' }}
      className="
        relative z-0 mx-4 cursor-pointer 
        w-full max-w-3xl 
        min-h-[400px] flex flex-col items-center justify-center
        rounded-[40px] 
        overflow-hidden
        transform-gpu isolation-isolate
        
        border border-white/30 bg-white/0 
        p-8 md:p-12 
        text-center shadow-2xl 
        backdrop-blur-[10px] backdrop-saturate-150 
        transition-all duration-500 ease-out 
        hover:scale-[1.02] hover:bg-white/5 active:scale-[0.98]
      "
    >
      <div className="pointer-events-none absolute -left-20 -top-20 h-56 w-56 rounded-full bg-white/20 blur-3xl" />
      
      {showClosedMessage ? (
        <div className="animate-in fade-in zoom-in duration-300 flex flex-col items-center z-10">
          <div className="mb-6 flex h-20 w-20 items-center justify-center rounded-full bg-red-500/10 border border-red-500/20 text-red-600 shadow-[0_0_30px_rgba(220,38,38,0.2)]">
             <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"/><line x1="15" y1="9" x2="9" y2="15"/><line x1="9" y1="9" x2="15" y2="15"/></svg>
          </div>
          
          <h2 className="text-3xl md:text-5xl font-black uppercase text-black mb-4 leading-none tracking-tight">
            Регистрация <br/> завершена
          </h2>
          
          <p className="mb-8 text-sm md:text-base font-bold text-black/60 uppercase tracking-widest max-w-md">
            К сожалению, прием заявок на хакатон был окончен 19 февраля в 15:00
          </p>

          <button 
            onClick={handleBackToMain}
            className="
              pointer-events-auto
              px-10 py-4 rounded-2xl bg-black text-white 
              font-bold text-sm uppercase tracking-wider 
              hover:bg-gray-900 transition-transform active:scale-95 shadow-lg
            "
          >
            Понятно
          </button>
        </div>
      ) : (
        <>
          <div className="relative z-10 flex flex-col items-center pointer-events-none">
            <span className="mb-4 text-sm font-bold tracking-[0.25em] text-black uppercase md:text-base">
              Itupolev
            </span>
            
            <h1 className="mb-6 text-4xl font-black leading-[0.9] text-black uppercase md:text-7xl lg:text-8xl">
              Tupolev IT <br /> Challenge
            </h1>
            
            <p className="text-xl font-extrabold text-black md:text-3xl tracking-wider">
              регистрация открыта
            </p>
            
            <p className="mt-6 text-xs font-semibold uppercase tracking-widest text-black/60 animate-pulse">
              Нажмите, чтобы подать заявку
            </p>

            <a 
              href={REGULATIONS_URL}
              target="_blank" 
              rel="noopener noreferrer"
              onClick={(e) => e.stopPropagation()} 
              className="
                pointer-events-auto
                mt-10
                border-b border-black/20 
                pb-0.5 
                text-[10px] font-bold uppercase tracking-widest text-black/40 
                transition-colors hover:border-black hover:text-black
              "
            >
              Положение о хакатоне
            </a>
          </div>

          <div className="
            mt-8 flex flex-col items-center pointer-events-none
            md:absolute md:bottom-8 md:right-10 md:mt-0 md:items-end
          ">
            <p className="text-[10px] md:text-xs font-bold uppercase tracking-tighter text-black">
              21-24 февраля
            </p>
            <p className="text-[8px] md:text-[10px] font-bold uppercase tracking-tight text-black/50 leading-none">
              IT-парк, ул. Петербургская, 52
            </p>
          </div>
        </>
      )}
    </div>
  );
};

export default LandingView;