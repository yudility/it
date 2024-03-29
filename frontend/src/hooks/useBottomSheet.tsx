import { useAnimation } from "framer-motion";
import { useEffect, useState } from "react";

import usePreviousValue from "./usePreviousValue";

const useBottomSheet = () => {
  const [isOpen, setIsOpen] = useState(true);
  const controls = useAnimation();
  const prevIsOpen = usePreviousValue(isOpen);

  // const onDragEnd = (info: any) => {
  //   const shouldClose = info?.y > 20 || (info?.y >= 0 && info.point.y > 45);

  //   if (shouldClose) {
  //     controls.start("hidden");
  //     setIsOpen(false);
  //   } else {
  //     controls.start("visible");
  //     setIsOpen(true);
  //   }
  // };

  // useEffect(() => {
  //   if (prevIsOpen && !isOpen) {
  //     controls.start("hidden");
  //   } else if (!prevIsOpen && isOpen) {
  //     controls.start("visible");
  //   }
  // }, [controls, isOpen, prevIsOpen]);
  const onDragEnd = (info: any) => {
    const shouldClose = info?.y > 20 || (info?.y >= 0 && info.point.y > 45);

    if (shouldClose) {
      setIsOpen(false);
    } else {
      setIsOpen(true);
    }
  };

  useEffect(() => {
    if (prevIsOpen !== isOpen) {
      // 상태가 변경될 때만 애니메이션 실행
      controls.start(isOpen ? "visible" : "hidden");
    }
  }, [controls, isOpen, prevIsOpen]);

  return { onDragEnd, controls, setIsOpen, isOpen };
};

export default useBottomSheet;
