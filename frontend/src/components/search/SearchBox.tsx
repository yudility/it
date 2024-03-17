import styled from "styled-components";
import LocationIcon from "../../assets/Location.svg";
import CloseIcon from "../../assets/Close.svg";
import ResetIcon from "../../assets/Reset.svg";
import SearchList from "./SearchList";
import WalkIcon from "../../assets/Walk.svg";
import BusIcon from "../../assets/Bus.svg";
import { WINDOW_HEIGHT } from "../../constants/Constants";
import SearchBar, { SearchBarProps } from "./SearchBar";
import { Building, Place, Route } from "../../pages/Map";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10 0px;
`;

const Title = styled.text`
  color: #191a23;
  text-align: center;
  font-feature-settings: "clig" off, "liga" off;
  font-family: Inter;
  font-size: 24px;
  font-style: normal;
  font-weight: 700;
  line-height: 150%;
  font-family: PretendardVariable;
  font-display: swap;
`;

const SearchWrapper = styled.button`
  display: flex;
  width: 327px;
  padding: 18px 16px;
  flex-direction: row;
  align-items: flex-start;
  gap: 10px;
  border-radius: 12px;
  background: #f8f8f8;
  border-color: transparent;
  font-family: PretendardVariable;
  &:hover {
    background-color: #00664f;
    color: #00664f;
    opacity: 0.5;
  }
  cursor: pointer;
  transition: all 0.3s;
`;

const Placeholder = styled.text<{ search: boolean }>`
  color: ${(props) => (props.search ? `black` : `#B4B4B4`)};
  font-family: Inter;
  font-size: 16px;
  font-style: normal;
  font-weight: 400;
  line-height: 120%;
  letter-spacing: -0.3px;
  font-family: PretendardVariable;
`;

interface SearchProps {
  start: string;
  end: string;
  onStart: () => void;
  onEnd: () => void;
}

interface BeforeSearchProps extends SearchProps {
  onFind: () => void;
}

export const BeforeSearch = ({
  start,
  end,
  onStart,
  onEnd,
  onFind,
}: BeforeSearchProps) => {
  return (
    <Container
      style={{
        justifyContent: "space-between",
        height: WINDOW_HEIGHT * 0.4 - 80,
      }}
    >
      <Title>어디로 가시겠어요?</Title>
      <SearchWrapper onClick={onStart}>
        <img src={LocationIcon} alt='현위치' />
        <Placeholder search={start ? true : false}>
          {start ? start : "출발지"}
        </Placeholder>
      </SearchWrapper>
      <SearchWrapper onClick={onEnd}>
        <img src={LocationIcon} alt='현위치' />
        <Placeholder search={end ? true : false}>
          {end ? end : "도착지"}
        </Placeholder>
      </SearchWrapper>
      <SearchWrapper
        style={{
          alignItems: "center",
          justifyContent: "center",
          backgroundColor: "#00664F",
          color: "white",
          fontSize: 16,
          fontWeight: "700",
        }}
        onClick={onFind}
      >
        경로 찾기
      </SearchWrapper>
    </Container>
  );
};

interface SearchResultProps extends SearchProps {
  onClose: () => void;
}

export const SearchResult = ({
  start,
  end,
  onStart,
  onEnd,
  onClose,
}: SearchResultProps) => {
  return (
    <Container
      style={{
        position: "absolute",
        left: 30,
        zIndex: 2,
        alignItems: "center",
        width: "90%",
        display: "flex",
        flexDirection: "row",
        justifyContent: "space-between",
      }}
    >
      <div>
        <SearchWrapper
          style={{
            padding: 10,
            backgroundColor: "white",
            marginBottom: 10,
            marginTop: 20,
          }}
          onClick={onStart}
        >
          <img src={LocationIcon} alt='현위치' />
          <Placeholder search={true}>출발지: {start}</Placeholder>
        </SearchWrapper>
        <SearchWrapper
          style={{ padding: 10, backgroundColor: "white" }}
          onClick={onEnd}
        >
          <img src={LocationIcon} alt='현위치' />
          <Placeholder search={true}>도착지: {end}</Placeholder>
        </SearchWrapper>
      </div>
      <img
        onClick={onClose}
        style={{ marginTop: 20 }}
        src={ResetIcon}
        alt='닫기'
      />
    </Container>
  );
};

interface OnSearchProps extends SearchBarProps {
  title: string;
  onClose: () => void;
  places: Place[];
  onSet: () => void;
}

export const OnSearch = ({
  title,
  onClose,
  search,
  setSearch,
  onChangeSearch,
  places,
  onSet,
}: OnSearchProps) => {
  return (
    <Container>
      <Title>{title} 검색</Title>
      <img
        onClick={onClose}
        style={{ position: "absolute", right: 30 }}
        src={CloseIcon}
        alt='닫기'
      />
      <SearchBar
        search={search}
        setSearch={setSearch}
        onChangeSearch={onChangeSearch}
      />
      {search.length > 0 && (
        <>
          <SearchList search={search} setSearch={setSearch} places={places} />
          <SearchWrapper
            style={{
              position: "absolute",
              bottom: WINDOW_HEIGHT * 0.2,
              alignItems: "center",
              justifyContent: "center",
              backgroundColor: "#00664F",
              color: "white",
              fontSize: 16,
              fontWeight: "700",
            }}
            onClick={onSet}
          >
            {title}로 설정
          </SearchWrapper>
        </>
      )}
    </Container>
  );
};

interface RouteResultProps {
  result: Route;
  option: string;
  onOption: () => void;
}

export const RouteResult = ({ result, option, onOption }: RouteResultProps) => {
  return (
    <Container
      style={{
        alignItems: "flex-end",
        padding: 10,
        paddingInline: 20,
        flexDirection: "row",
        justifyContent: "flex-start",
      }}
    >
      <Title>소요 시간</Title>
      <Title style={{ fontSize: 20, marginLeft: 15, marginBottom: 2 }}>
        {option + " " + result.minutes}분
      </Title>
      <img
        src={option === "도보" ? WalkIcon : BusIcon}
        alt='옵션'
        style={{ marginBottom: 6, marginLeft: 10, marginRight: 50 }}
      />
      <button
        onClick={onOption}
        style={{
          background: '#00664F',
          borderColor: 'transparent',
          height: 35,
          width: 80,
          borderRadius: 12,
          fontFamily: 'PretendardVariable',
          fontSize: 16,
          color: 'white',
          fontWeight: 700,
          flex: 1
        }}
      >
        {option === "도보" ? "셔틀" : "도보"}
      </button>
    </Container>
  );
};
