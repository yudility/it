import React, { Dispatch, SetStateAction } from 'react';
import styled from 'styled-components';

const Wrapper = styled.div`
  background: grey;
  width: 100%;
  flex-direction: row;
  display: flex;
  position: absolute;
  z-index: 2;
`

const SearchInput = styled.input`
  color: black;
  padding: auto;
  line-height: 3vw;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 900;
  width: 100%;
  height: 50px;
  font-size: 24px;
`

interface SearchBarProps {
  search: string;
  onChangeSearch: any;
}

export default function SearchBar ({ search, onChangeSearch }: SearchBarProps ) {
  return (
    <Wrapper>
      <SearchInput
        value={search}
        onChange={onChangeSearch}
      />
      <button>검색</button>
    </Wrapper>
  )
}