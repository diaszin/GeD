import { LucideTrash2 } from "lucide-react";
import { Button } from "./ui/button";
import {
  AlertDialog,
  AlertDialogTitle,
  AlertDialogTrigger,
  AlertDialogContent,
  AlertDialogFooter,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogDescription,
} from "./ui/alert-dialog";
import type { MouseEventHandler } from "react";

interface DeleteWithAlertProps {
  message: string;
  alertTitle: string;
  confirmButtonText?: string;
  cancelButtonText?: string;
  confirmAction: MouseEventHandler<HTMLButtonElement>;
}

export default function DeleteButtonWithAlert(props: DeleteWithAlertProps) {
  return (
    <AlertDialog>
      <AlertDialogTrigger>
        <Button asChild variant="outline">
          <LucideTrash2 className="w-14 text-destructive" />
        </Button>
      </AlertDialogTrigger>
      <AlertDialogContent>
        <AlertDialogTitle>{props.alertTitle}</AlertDialogTitle>
        <AlertDialogDescription>{props.message}</AlertDialogDescription>
        <AlertDialogFooter>
          <AlertDialogCancel>
            {props.cancelButtonText || "Cancelar"}
          </AlertDialogCancel>
          <AlertDialogAction className="bg-destructive" onClick={props.confirmAction}>
            {props.confirmButtonText || "Confirmar"}
          </AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  );
}
