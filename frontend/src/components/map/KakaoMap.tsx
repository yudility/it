import React, { useEffect, useState, useRef, MutableRefObject } from "react";
import Locations from "./Locations";
import { positions } from "../../data/Positions";
import useKakaoLoader from "./useKakaoLoader";
import { Map, MapMarker, Polyline, MapTypeId } from "react-kakao-maps-sdk";
import CurrentIcon from "../../assets/Current.svg";
import MarkerIcon from "../../assets/Marker.svg";

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
          draggable={true}
        >
          <MapMarker
            position={{
              lat: location.latitude,
              lng: location.longitude,
            }}
            image={{
              src: CurrentIcon,
              size: {
                width: 30,
                height: 30,
              },
            }}
          />
          {/* <MapTypeId type={"ROADVIEW"} /> */}
          <Polyline
            path={[
              [
                { lat: 37.559716, lng: 126.945468 },
                { lat: 37.561085, lng: 126.94512 },
                { lat: 37.56353, lng: 126.944546 },
              ],
            ]}
            strokeWeight={5} // 선의 두께 입니다
            strokeColor={"#FF0000"} // 선의 색깔입니다
            strokeStyle={"solid"} // 선의 스타일입니다
          />
          {positions.map((position, index) => {
            return (
              position.info !== '없음' ? 
                <MapMarker
                  key={index}
                  position={position.latlng} // 마커를 표시할 위치
                  image={{
                    src: MarkerIcon,
                    size: {
                      width: 24,
                      height: 35,
                    },
                  }}
                >
                  <div>
                    {position.info}
                  </div>
                </MapMarker>
              :
              <MapMarker
                key={index}
                position={position.latlng} // 마커를 표시할 위치
                image={{
                  src: MarkerIcon,
                  size: {
                    width: 24,
                    height: 35,
                  },
                }}
              />
            )
          })}
        </Map>
      )}
    </>
  );
}
