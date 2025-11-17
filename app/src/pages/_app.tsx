import UserLoginExpiredAlert from "@/components/UserLoginExpired";
import { useEffect, useState } from "react";
import { Outlet } from "react-router";

export default function AllPages() {
  const [showModal, setShowModal] = useState<boolean>(true);

  useEffect(() => {
    window.addEventListener("axios-403", () => {
      setShowModal(false);
    });

    return () => {
      window.removeEventListener("axios-403", () => {});
    };
  }, []);

  return (
    <>
      <UserLoginExpiredAlert
        onClick={() => {
          setShowModal(true);
        }}
        isExpired={!showModal}
      />

      <Outlet />
    </>
  );
}
