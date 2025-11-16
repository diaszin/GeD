import React from "react";
import {
  AlertDialog,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "./ui/alert-dialog";
import { Button } from "./ui/button";
import { Link } from "@/router";

interface UserLoginExpiredAlertProps {
  isExpired: boolean;
  onClick: () => void;
}

export default function UserLoginExpiredAlert(
  props: UserLoginExpiredAlertProps
) {
  return (
    <AlertDialog open={props.isExpired}>
      <AlertDialogContent>
        <AlertDialogHeader>
          <AlertDialogTitle>Sua sessão expirou</AlertDialogTitle>
        </AlertDialogHeader>
        <AlertDialogDescription>
          Opa! Sua sessão expirou. Faça login novamente para continuar usando o
          sistema
        </AlertDialogDescription>
        <AlertDialogFooter>
          <Link to="/signin">
            <Button onClick={props.onClick} className="w-full">
              Realizar login novamente
            </Button>
          </Link>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  );
}
