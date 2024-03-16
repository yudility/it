import { Dispatch, SetStateAction } from "react";
import styled from "styled-components";
import CheckIcon from "../../assets/Check.svg";
import { Place } from "../../pages/Map";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  width: 95%;
  margin-top: 20px;
  margin-horizontal: 20px;
`;

const TextWrapper = styled.div`
  margin-bottom: 10px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 95%;
`;

const ItemText = styled.text`
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: start;
  font-size: 16px;
  padding: 10px;
  font-weight: 500;
  font-family: PretendardVariable;
`;

interface SearchListProps {
  search: string;
  setSearch: Dispatch<SetStateAction<string>>;
  places: Place[];
}

export default function SearchList({
  search,
  setSearch,
  places,
}: SearchListProps) {
  console.log(places);
  return (
    <Container>
      {places.length > 0 &&
        places.map(({ id, building }: any) => {
          return (
            <TextWrapper key={id} onClick={() => setSearch(building.name)}>
              <ItemText>{building.name}</ItemText>
              {search === building.name && <img src={CheckIcon} alt='체크' />}
            </TextWrapper>
          );
        })}
    </Container>
  );
}
