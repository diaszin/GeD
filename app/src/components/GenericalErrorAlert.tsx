import {
  AlertDialog,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "./ui/alert-dialog";
import { Button } from "./ui/button";

export default function GenericalErrorAlert() {
  return (
    <AlertDialog open>
      <AlertDialogContent>
        <AlertDialogHeader>
          <AlertDialogTitle>
            Erro desconhecido. Tente novamente mais tarde !
          </AlertDialogTitle>
        </AlertDialogHeader>
        <AlertDialogDescription>
          Ocorreu um erro inesperado. Nossa equipe já foi notificada e estamos
          trabalhando para resolver o problema.
        </AlertDialogDescription>
        <AlertDialogFooter>
          <Button className="w-full">Realizar login novamente</Button>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  );
}
