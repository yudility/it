import React, { useEffect, useState, useRef, MutableRefObject } from "react";
import Locations from "./Locations";
import { positions } from "../../data/Positions";
import useKakaoLoader from "./useKakaoLoader";
import { Map, MapMarker } from "react-kakao-maps-sdk";
import BottomSheet from "../bottomsheet/BottomSheet";

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

  // 검색
  useEffect(() => {
    if (!map || !search) return;
    console.log("search", search);
    const ps = new kakao.maps.services.Places();
    ps.keywordSearch(search, (data, status, _pagination) => {
      if (status === kakao.maps.services.Status.OK) {
        // console.log(data);
        const bounds = new kakao.maps.LatLngBounds();
        let markers = [];

        for (var i = 0; i < data.length; i++) {
          markers.push({
            position: {
              lat: parseInt(data[i].y),
              lng: parseInt(data[i].x),
            },
            content: data[i].place_name,
          });
          bounds.extend(
            new kakao.maps.LatLng(parseInt(data[i].y), parseInt(data[i].x))
          );
        }
        setMarkers(markers);
        map.setBounds(bounds);
      }
    });
    console.log(markers)
  }, [search, map]);

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
          {positions.map((position, index) => (
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
          ))}
          {markers && markers.map((marker: any) => (
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
          ))}
          <BottomSheet>
            <span>Content</span>
          </BottomSheet>
        </Map>
      )}
    </>
  );
}
