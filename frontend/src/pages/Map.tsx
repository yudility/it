import React, { useState, useRef, useEffect } from "react";
import KakaoMap from "../components/map/KakaoMap";
import SearchBar from "../components/search/SearchBar";
import BottomSheet from "../components/bottomsheet/BottomSheet";
import styled from "styled-components";
import LocationIcon from '../assets/Location.svg';
import CloseIcon from '../assets/Close.svg';
import SearchList from "../components/search/SearchList";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10 0px;
`

const Title = styled.text`
  color: #191A23;
  text-align: center;
  font-feature-settings: 'clig' off, 'liga' off;
  font-family: Inter;
  font-size: 24px;
  font-style: normal;
  font-weight: 700;
  line-height: 150%;
  margin-bottom: 20px;
`

const SearchWrapper = styled.button`
  display: flex;
  width: 327px;
  padding: 18px 16px;
  flex-direction: row;
  align-items: flex-start;
  gap: 10px;
  border-radius: 12px;
  background: #F8F8F8;
  border-color: transparent;
  margin-vertical: 10px;
`

const Placeholder = styled.text<{ search: boolean }>`
  color: ${props => props.search ? `black` :`#B4B4B4`};
  font-family: Inter;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 120%;
  letter-spacing: -0.3px;
`

export default function Map() {
  const [search, setSearch] = useState<string>("");
  const onChangeSearch = (e: any) => {
    e.preventDefault();
    setSearch(e.target.value);
  };
  const [searchMode, setSearchMode] = useState<boolean>(false);
  const [title, setTitle] = useState<string>('');
  const [start, setStart] = useState<string>('');
  const [dest, setDest] = useState<string>('');
  
  const [result, setResult] = useState<boolean>(false);

  return (
    <>
      <KakaoMap search={search} />
      <BottomSheet searchMode={searchMode}>
        { searchMode ? (
          <Container>
            <Title>{title} 검색</Title>
            <img onClick={() => {setSearchMode(false); setSearch('')}} style={{position: 'absolute', right: 30}} src={CloseIcon} alt='닫기' />
            <SearchBar search={search} setSearch={setSearch} onChangeSearch={onChangeSearch} />
            {
              search.length > 0 && (
                <>
                  <SearchList search={search} setSearch={setSearch} />
                  <SearchWrapper
                    style={{position: 'absolute', top: 630, alignItems: 'center', justifyContent: 'center', backgroundColor: '#00664F', color: 'white', fontSize: 16, fontWeight: '700'}}
                    onClick={() => {title === '출발지' ? (setStart(search)) : (setDest(search)); setSearch(''); setSearchMode(false)}}>
                    {title}로 설정
                  </SearchWrapper>
                </>
              )
            }
          </Container>
        ) : (
          <Container>
            <Title>어디로 가시겠어요?</Title>
            <SearchWrapper style={{marginBottom: 20}} onClick={() => {setTitle('출발지'); setSearchMode(true)}}>
              <img src={LocationIcon} alt='현위치' />
              <Placeholder search={!!start}>{start ? start : '출발지'}</Placeholder>
            </SearchWrapper>
            <SearchWrapper style={{marginBottom: 20}} onClick={() => {setTitle('도착지'); setSearchMode(true)}}>
              <img src={LocationIcon} alt='현위치' />
              <Placeholder search={!!dest}>{dest ? dest : '도착지'}</Placeholder>
            </SearchWrapper>
            <SearchWrapper
              style={{alignItems: 'center', justifyContent: 'center', backgroundColor: '#00664F', color: 'white', fontSize: 16, fontWeight: '700'}}
              onClick={() => {}}>
              경로 찾기
            </SearchWrapper>
          </Container>
        )}
      </BottomSheet>
    </>
  );
}
