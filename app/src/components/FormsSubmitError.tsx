import type { AxiosError } from "axios";
import { AlertCircleIcon } from "lucide-react";
import { Alert, AlertTitle, AlertDescription } from "./ui/alert";

interface FormsSubmitErrorProps {
  isError: boolean;
  error: AxiosError<{
    message: string | string[];
  }> | null;
  title?: string
}



export function FormsSubmitError(props: FormsSubmitErrorProps) {
  if (!props.isError || !props.error) {
    return null;
  }

  const messages = props.error.response?.data.message;

  return (
    <Alert variant="destructive">
      <AlertCircleIcon />
      {props.title && <AlertTitle>{props.title}</AlertTitle>}
      <AlertDescription>
        <p>Verifique e tente novamente</p>
        <ul className="list-inside list-disc text-sm">
          {Array.isArray(messages) ? (
            messages.map((message) => <li>{message}</li>)
          ) : (
            <li>{messages}</li>
          )}
        </ul>
      </AlertDescription>
    </Alert>
  );
}