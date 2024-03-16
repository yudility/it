import useBottomSheet from "../../hooks/useBottomSheet";
import { useState, useEffect, useRef, SetStateAction, Dispatch } from "react";
import * as S from "./BottomSheet.style";
import Header from "./Header";

const BottomSheet = ({ children, mode, setMode }: { children: any; mode: string, setMode: Dispatch<SetStateAction<string>> }) => {
  const { onDragEnd, controls } = useBottomSheet();
  const [height, setHeight] = useState<number>(350);
  useEffect(() => {
    if (height === 350 && mode === "toCurrent") setMode("beforeSearch");
    if (mode === "onSearch") {
      setHeight(0);
    } else if (mode === "beforeSearch" || mode === "toCurrent") {
      setHeight(350);
    } else if (mode === "result") {
      setHeight(580);
    }
  }, [mode]);

  useEffect(() => {
    // height 상태가 변경될 때마다 애니메이션을 다시 시작
    controls.start({
      y: height,
      transition: {
        type: "spring",
        damping: 40,
        stiffness: 400,
      },
    });
  }, [mode, height, controls]);

  return (
    <S.Wrapper
      drag='y'
      onDragEnd={onDragEnd}
      initial='visible'
      animate={controls}
      transition={{
        type: "spring",
        damping: 40,
        stiffness: 400,
      }}
      variants={{
        visible: { y: height },
        hidden: { y: "100%" },
      }}
      dragConstraints={{ top: 100 }}
      dragElastic={0.2}
    >
      <Header />
      <S.ContentWrapper>{children}</S.ContentWrapper>
    </S.Wrapper>
  );
};

export default BottomSheet;
