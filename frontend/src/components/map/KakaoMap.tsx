import React, { useEffect, useState, useRef, MutableRefObject } from "react";
import Locations from "./Locations";
import { positions } from "../../data/Positions";
import useKakaoLoader from "./useKakaoLoader";
import { Map, MapMarker, Polyline } from "react-kakao-maps-sdk";

interface marker {
  position: {
    lat: number;
    lng: number;
  };
  content: string;
}

export default function KakaoMap({ search }: { search: string }) {
  useKakaoLoader();
  const location: any = Locations();
  const [map, setMap] = useState<kakao.maps.Map>();
  const [info, setInfo] = useState<marker>();
  const [markers, setMarkers] = useState<marker[]>([
    {
      position: {
        lat: 36.7,
        lng: 123.7,
      },
      content: "",
    },
  ]);

  return (
    <>
      {typeof location != "string" && (
        <Map
          id='map'
          center={{
            lat: location.latitude,
            lng: location.longitude,
          }}
          style={{
            width: "100%",
            height: "100vh",
          }}
          level={3}
          onCreate={setMap}
        >
          <MapMarker
            position={{
              lat: location.latitude,
              lng: location.longitude,
            }}
          />
          {/* <Polyline
            path={[
              [
                { lat: 33.452344169439975, lng: 126.56878163224233 },
                { lat: 33.452739313807456, lng: 126.5709308145358 },
                { lat: 33.45178067090639, lng: 126.572688693875 },
              ],
            ]}
            strokeWeight={5} // 선의 두께 입니다
            strokeColor={"#FFAE00"} // 선의 색깔입니다
            strokeOpacity={0.7} // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            strokeStyle={"solid"} // 선의 스타일입니다
          /> */}
          {/* {positions.map((position, index) => (
            <MapMarker
              key={`${position.title}-${position.latlng}`}
              position={position.latlng} // 마커를 표시할 위치
              image={{
                src: "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
                size: {
                  width: 24,
                  height: 35,
                },
              }}
              title={position.title} // 마우스 올리면 표시
            />
          ))} */}
          {/* {markers && markers.map((marker: any) => (
            <MapMarker
              key={`marker-${marker.content}-${marker.position.lat},${marker.position.lng}`}
              position={marker.position}
              onClick={() => setInfo(marker)}
              image={{
                src: "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
                size: {
                  width: 24,
                  height: 35,
                },
              }}
              title={marker.content}
            >
              {info && info.content === marker.content && <div style={{ color: '#000' }}>{marker.content}</div>}
            </MapMarker>
          ))} */}
        </Map>
      )}
    </>
  );
}
