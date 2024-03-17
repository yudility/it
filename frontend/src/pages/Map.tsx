import React, { useState, useEffect } from "react";
import KakaoMap from "../components/map/KakaoMap";
import BottomSheet from "../components/bottomsheet/BottomSheet";
import styled from "styled-components";
import LogoIcon from "../assets/Logo.svg";
import Request from "../services/requests";
import {
  BeforeSearch,
  OnSearch,
  RouteResult,
  SearchResult,
} from "../components/search/SearchBox";

const Header = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  background: #00664f;
`;

export interface Building {
  id: number;
  name: string;
  info: string | null;
}

export interface Place {
  building: Building | null;
  id: number;
  latitude: number;
  longitude: number;
}

export interface Route {
  start: Place;
  end: Place;
  minutes: number;
  vertexList: Place[];
}

export const defaultResult = {
  start: {
    id: 0,
    latitude: 0,
    longitude: 0,
    building: {
      id: 0,
      info: null,
      name: "",
    },
  },
  end: {
    id: 0,
    latitude: 0,
    longitude: 0,
    building: {
      id: 0,
      info: null,
      name: "",
    },
  },
  minutes: 0,
  vertexList: [
    {
      id: 0,
      latitude: 0,
      longitude: 0,
      building: {
        id: 0,
        info: null,
        name: "",
      },
    },
  ],
};

export default function Map() {
  const request = Request();
  const [search, setSearch] = useState<string>("");
  const onChangeSearch = (e: any) => {
    e.preventDefault();
    setSearch(e.target.value);
  };
  const [mode, setMode] = useState<string>("beforeSearch");
  const [title, setTitle] = useState<string>("");
  const [start, setStart] = useState<string>("");
  const [end, setEnd] = useState<string>("");
  const [places, setPlaces] = useState<Place[]>([
    {
      id: 0,
      latitude: 0,
      longitude: 0,
      building: {
        id: 0,
        info: null,
        name: "",
      },
    },
  ]);
  const [result, setResult] = useState<Route>(defaultResult);
  const [option, setOption] = useState<string>('도보');

  const searchByName = async () => {
    const response = await request.get("point/find", {
      name: search,
    });
    setPlaces(response.data);
  };

  const findRoute = async () => {
    if (start && end) {
      const response = await request.get("route/find", {
        start: start,
        end: end,
      });
      setMode("afterSearch");
      setResult(response.data);
    } else {
      alert("출발지와 도착지를 모두 설정해주세요!");
    }
  };

  useEffect(() => {
    if (search.length > 0) searchByName();
  }, [search]);

  return (
    <>
      <Header>
        <img
          src={LogoIcon}
          alt='로고'
          style={{ height: 40, alignSelf: "center" }}
          onClick={() => {
            setMode("toCurrent");
          }}
        />
      </Header>
      {mode === "afterSearch" && (
        <SearchResult
          start={start}
          end={end}
          onStart={() => {
            setTitle("출발지");
            setMode("onSearch");
          }}
          onEnd={() => {
            setTitle("도착지");
            setMode("onSearch");
          }}
          onClose={() => {
            setMode("toCurrent");
            setSearch("");
            setStart("");
            setEnd("");
          }}
        />
      )}
      <KakaoMap mode={mode} result={result} setResult={setResult} />
      <BottomSheet mode={mode} setMode={setMode}>
        {mode === "onSearch" ? (
          <OnSearch
            title={title}
            onClose={() => {
              setMode("beforeSearch");
              setSearch("");
            }}
            search={search}
            setSearch={setSearch}
            onChangeSearch={onChangeSearch}
            places={places}
            onSet={() => {
              title === "출발지" ? setStart(search) : setEnd(search);
              setSearch("");
              setMode("beforeSearch");
            }}
          />
        ) : mode === "afterSearch" ? (
          <RouteResult result={result} option={option} onOption={() => setOption(option === '도보' ? '셔틀' : '도보')} />
        ) : (
          <BeforeSearch
            start={start}
            end={end}
            onStart={() => {
              setTitle("출발지");
              setMode("onSearch");
            }}
            onEnd={() => {
              setTitle("도착지");
              setMode("onSearch");
            }}
            onFind={findRoute}
          />
        )}
      </BottomSheet>
    </>
  );
}
