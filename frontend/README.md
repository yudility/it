### 사용 스택
- React, Typescript, Styled-Components, Kakao Map API

### 주요 기능
- 현위치 탐색
- 교내 건물 검색
- 교내 건물 간 길찾기

### Frontend 폴더 구조
```bash
.
├── App.tsx
├── assets
│   └── fonts
│       └── PretendardVariable.woff2
├── components
│   ├── bottomsheet // 검색창 모달
│   │   ├── BottomSheet.style.ts
│   │   ├── BottomSheet.tsx
│   │   └── Header.tsx
│   ├── map // KakaoMap 화면
│   │   ├── KakaoMap.tsx
│   │   └── Locations.tsx
│   └── search  // 검색 관련 컴포넌트
│       ├── SearchBar.tsx
│       ├── SearchBox.tsx
│       └── SearchList.tsx
├── constants // 상수값
│   └── Constants.js
├── fonts.d.ts
├── hooks // 커스텀 훅
│   ├── useBottomSheet.tsx  // 바텀 시트 모달 훅
│   ├── useKakaoLoader.tsx  // 카카오맵 로더 훅
│   └── usePreviousValue.tsx 
├── pages // 페이지
│   └── Map.tsx
├── services  // API 연결
│   └── requests.js
└── styles  // 전역 스타일
    └── global.ts
```
