import React from 'react';

interface Sponsor {
  id: number;
  name: string;
  logoUrl: string;
}

const SPONSORS_DATA: Sponsor[] = [
  { id: 1, name: 'Apex', logoUrl: '/apex.png' },
  { id: 2, name: 'Baklazhan', logoUrl: '/itpark.png' },
  { id: 3, name: 'Digital Kai', logoUrl: '/digital_kai.png' },
  { id: 4, name: 'Parnas IT', logoUrl: '/kai.png' },
  { id: 5, name: 'SimbirSoft', logoUrl: '/baklazhan.png' },
  { id: 6, name: 'Kai', logoUrl: '/parnas.png' },
  { id: 7, name: 'It Park', logoUrl: '/simbir.png' },
  { id: 8, name: 'BAS', logoUrl: '/BAS.png' },
];

const SponsorsHeader: React.FC = () => {
  return (
    <header className="
      z-10 w-full

      /* Mobile: relative - занимает место, но мы уменьшили padding (py-3) */
      relative px-4 py-3
      
      /* Desktop: absolute - возвращаем как было */
      md:absolute md:top-0 md:left-0 md:px-16 md:py-8

      flex flex-wrap justify-center items-center 
      
      /* Mobile: ОЧЕНЬ компактные отступы (gap-y-2), чтобы ряды не занимали пол-экрана */
      gap-y-2 gap-x-4
      
      /* Desktop: нормальные отступы */
      md:flex-nowrap md:justify-between md:gap-y-0
    ">
      {SPONSORS_DATA.map((sponsor) => (
        <img 
          key={sponsor.id} 
          src={sponsor.logoUrl} 
          alt={sponsor.name} 
          className="
            pointer-events-auto 
            object-contain 
            
            /* Mobile: уменьшили высоту до h-5 (20px), чтобы сэкономить место */
            h-5 w-auto
            
            /* Desktop: увеличиваем обратно */
            md:h-12 lg:h-16 md:w-auto
          " 
        />
      ))}
    </header>
  );
};

export default SponsorsHeader;