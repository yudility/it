import React, { useEffect, useState, useRef, MutableRefObject } from "react";
import Locations from "./Locations";

export default function KakaoMap() {
  const mapRef = useRef<HTMLElement | null>(null);
  const location:any = Locations();

  const initMap = () => {
    if (typeof location != 'string' ){
      const container = document.getElementById('map');
      const options = {
        center: new kakao.maps.LatLng(location.latitude, location.longitude),
        level: 4
      };
  
      var map = new kakao.maps.Map(container as HTMLElement, options);
      (mapRef as MutableRefObject<any>).current = map;
    }
  };

  useEffect(() => {
    kakao.maps.load(() => initMap());
  }, [mapRef, location]);

  return (
    <>
      <div id='map' style={{ width: "100%", height: "90vh" }}></div>
    </>
  );
}
