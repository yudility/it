import React, {
  useEffect,
  useState,
  useRef,
  MutableRefObject,
  useMemo,
} from "react";
import Locations from "./Locations";
import styled from "styled-components";
import { positions } from "../../data/Positions";
import useKakaoLoader from "./useKakaoLoader";
import {
  Map,
  MapMarker,
  Polyline,
  MapTypeId,
  CustomOverlayMap,
} from "react-kakao-maps-sdk";
import CurrentIcon from "../../assets/Current.svg";
import MarkerIcon from "../../assets/Marker.svg";

export default function KakaoMap({ result }: { result: string }) {
  useKakaoLoader();
  const location: any = Locations();
  const [map, setMap] = useState<kakao.maps.Map>();

  useEffect(() => {
    if (result === "result") {
      resetBounds(positions);
    }
  }, [result]);

  const resetBounds = (data: any) => {
    const bounds = new kakao.maps.LatLngBounds();
    for (var i = 0; i < data.length; i++) {
      bounds.extend(
        new kakao.maps.LatLng(data[i].latlng.lat, data[i].latlng.lng)
      );
    }
    map!.setBounds(bounds);
  };

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
          <Polyline
            path={[
              [
                { lat: 37.559716, lng: 126.945468 },
                { lat: 37.561085, lng: 126.94512 },
                { lat: 37.56353, lng: 126.944546 },
              ],
            ]}
            strokeWeight={5} // 선의 두께 입니다
            strokeColor={"#00664F"} // 선의 색깔입니다
            strokeStyle={"solid"} // 선의 스타일입니다
          />
          {positions.map((position, index) => {
            return (
              <>
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
                {position.info && (
                  <CustomOverlayMap // 커스텀 오버레이를 표시할 Container
                    position={position.latlng}
                  >
                    <div
                      className='label'
                      style={{
                        fontFamily: "PretendardVariable",
                        backgroundColor: "white",
                        padding: 5,
                        borderRadius: 8,
                        opacity: 0.9,
                      }}
                    >
                      <span className='left'></span>
                      <span className='center'>{position.info}</span>
                      <span className='right'></span>
                    </div>
                  </CustomOverlayMap>
                )}
              </>
            );
          })}
        </Map>
      )}
    </>
  );
}
