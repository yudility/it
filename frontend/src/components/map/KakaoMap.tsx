import { SetStateAction, useEffect, useState, Dispatch } from "react";
import Locations from "./Locations";
import useKakaoLoader from "../../hooks/useKakaoLoader";
import {
  Map,
  MapMarker,
  Polyline,
  CustomOverlayMap,
} from "react-kakao-maps-sdk";
import CurrentIcon from "../../assets/Current.svg";
import MarkerIcon from "../../assets/Marker.svg";
import { Route, defaultResult } from "../../pages/Map";

export default function KakaoMap({
  mode,
  result,
  setResult,
}: {
  mode: string;
  result: Route;
  setResult: Dispatch<SetStateAction<Route>>;
}) {
  useKakaoLoader();
  const location: any = Locations();
  const path: { lat: number; lng: number }[] = result.vertexList.map(
    (vertex) => ({
      lat: vertex.latitude,
      lng: vertex.longitude,
    })
  );
  const [map, setMap] = useState<kakao.maps.Map>();

  useEffect(() => {
    if (mode === "afterSearch") {
      resetBounds(path);
    } else if (mode === "toCurrent") {
      map!.setCenter(
        new kakao.maps.LatLng(location.latitude, location.longitude)
      );
      setResult(defaultResult);
    }
  }, [mode, map]);

  const resetBounds = (data: any) => {
    const bounds = new kakao.maps.LatLngBounds();
    for (var i = 0; i < data.length; i++) {
      bounds.extend(new kakao.maps.LatLng(data[i].lat, data[i].lng));
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
            height: "95vh"
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
          {result.minutes > 0 && (
            <>
              <Polyline
                path={path}
                strokeWeight={5} // 선의 두께 입니다
                strokeColor={"#00664F"} // 선의 색깔입니다
                strokeStyle={"solid"} // 선의 스타일입니다
              />
              {result.vertexList.map((vertex, index) => {
                return (
                  <>
                    {vertex.building && (
                      <MapMarker
                        key={index}
                        position={{
                          lat: vertex.latitude,
                          lng: vertex.longitude,
                        }} // 마커를 표시할 위치
                        image={{
                          src: MarkerIcon,
                          size: {
                            width: 24,
                            height: 35,
                          },
                        }}
                      />
                    )}
                    {/* {vertex.building !== null &&
                      vertex.building!.info !== null && (
                        <CustomOverlayMap // 커스텀 오버레이를 표시할 Container
                          position={{
                            lat: vertex.latitude,
                            lng: vertex.longitude,
                          }}
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
                            <span className='center'>
                              {vertex.building!.info!}
                            </span>
                            <span className='right'></span>
                          </div>
                        </CustomOverlayMap>
                      )} */}
                  </>
                );
              })}
            </>
          )}
        </Map>
      )}
    </>
  );
}
