import React, { useEffect, useState } from "react";

export default function KakaoMap() {
  const [map, setMap] = useState();
  useEffect(() => {
    const mapScript = document.createElement("script");

    mapScript.async = true;
    mapScript.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.REACT_APP_KAKAO_KEY}&autoload=false`;

    document.head.appendChild(mapScript);

    const onLoadKakaoMap = () => {
      window.kakao.maps.load(() => {
        const mapContainer = document.getElementById("map");
        const mapOption = {
          center: new window.kakao.maps.LatLng(37.5635, 126.9467), // 지도의 중심좌표
          level: 4, // 지도의 확대 레벨
        };
        setMap(new window.kakao.maps.Map(mapContainer, mapOption));
      });
    };
    mapScript.addEventListener("load", onLoadKakaoMap);
  }, []);

  // 지도에 선을 표시한다
  // var polyline = new window.kakao.maps.Polyline({
  //   map: map, // 선을 표시할 지도 객체
  //   path: [
  //     // 선을 구성하는 좌표 배열
  //     new window.kakao.maps.LatLng(37.5635, 126.948),
  //     new window.kakao.maps.LatLng(37.563, 126.9476),
  //     new window.kakao.maps.LatLng(37.5648, 126.9467),
  //   ],
  //   strokeWeight: 3, // 선의 두께
  //   strokeColor: "#FF0000", // 선 색
  //   strokeOpacity: 0.9, // 선 투명도
  //   strokeStyle: "solid", // 선 스타일
  // });

  return (
    <div
      id='map'
      style={{
        width: "100%",
        height: "100vh",
      }}
    ></div>
  );
}
