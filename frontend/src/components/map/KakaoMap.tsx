import React, { useEffect, useState, useRef, MutableRefObject } from "react";
import Locations from "./Locations";
import { positions } from "../../data/Positions";

export default function KakaoMap() {
  const mapRef = useRef<HTMLElement | null>(null);
  const location:any = Locations();

  const initMap = () => {
    if (typeof location != 'string' ){
      const container = document.getElementById('map');
      const locPosition = new kakao.maps.LatLng(location.latitude, location.longitude);
      const options = {
        center: locPosition,
        level: 3
      };
  
      var map = new kakao.maps.Map(container as HTMLElement, options);
      (mapRef as MutableRefObject<any>).current = map;
      const message = '현위치'
      currentLocation({ map, locPosition, message });
      showMarkers({map});
      showPath({map})
    }
  };

  useEffect(() => {
    kakao.maps.load(() => initMap());
  }, [mapRef, location]);

  const currentLocation = ({ map, locPosition, message }: { map: any; locPosition: any; message: string; }) => {
    var marker = new kakao.maps.Marker({  
      map: map, 
      position: locPosition
    }); 
    var iwContent = message, // 인포윈도우에 표시할 내용
      iwRemoveable = true;
    var infowindow = new kakao.maps.InfoWindow({
      content : iwContent,
      removable : iwRemoveable
    });
    infowindow.open(map, marker);
    map.setCenter(locPosition);   
  }

  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
    
  const showMarkers = ({ map }: { map: any }) => {
    for (var i = 0; i < positions.length; i ++) {
      var imageSize = new kakao.maps.Size(24, 35);  
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
      var marker = new kakao.maps.Marker({
        map: map,
        position: positions[i].latlng, // 좌표
        title : positions[i].name, // 마커 타이틀. 마우스 올리면 표시.
        image : markerImage // 마커 이미지 
      });
    }  
  }

  const tmp = [positions[0].latlng, positions[1].latlng];
  const showPath = ({ map }: { map: any }) => {
    var polyline = new kakao.maps.Polyline({
      path: tmp, // 좌표배열
      strokeWeight: 5, // 선 두께
      strokeColor: '#FFAE00', // 선 색깔
      strokeOpacity: 0.7, // 선 불투명도. 1~0. 0에 가까울수록 투명.
      strokeStyle: 'solid' // 선 스타일
    });
    polyline.setMap(map);
  }

  return (
    <>
      <div id='map' style={{ width: "100%", height: "90vh" }}></div>
      <button
        style={{ position:"relative", zIndex:"2"}}
        onClick={() => initMap()}
      >현위치로</button>
    </>
  );
}
