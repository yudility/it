import { createGlobalStyle } from 'styled-components';
import PretendardVariable from '../assets/fonts/PretendardVariable.woff2';

const GlobalStyle = createGlobalStyle`
  @font-face {
    font-family: 'PretendardVariable';
    font-style: normal;
    src: url(${PretendardVariable}) format('truetype');
  }
`

export default GlobalStyle;