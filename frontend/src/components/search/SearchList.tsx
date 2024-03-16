import { Dispatch, SetStateAction, useState } from "react";
import styled from "styled-components";
import CheckIcon from "../../assets/Check.svg";

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

const data = [
  "이화여자대학교 학생문화관",
  "이화여자대학교 아산공학관",
  "이화여자대학교 대강당",
  "이화여자대학교 포스코관",
];

interface SearchListProps {
  search: string;
  setSearch: Dispatch<SetStateAction<string>>;
}

export default function SearchList({ search, setSearch }: SearchListProps) {
  return (
    <Container>
      {data.map((item, index) => {
        return (
          <TextWrapper key={index} onClick={() => setSearch(item)}>
            <ItemText>{item}</ItemText>
            {search === item && <img src={CheckIcon} alt='체크' />}
          </TextWrapper>
        );
      })}
    </Container>
  );
}
