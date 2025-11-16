import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import z from "zod";

export type FileUploadFormType = z.infer<typeof fileUploadFormSchema>;

const fileUploadFormSchema = z.object({
  file: z.any().refine((file) => file !== undefined, "Arquivo é obrigatório"),
  title: z.string().nonempty(),
});

export function useFileUploadForm() {
  const form = useForm({
    resolver: zodResolver(fileUploadFormSchema),
    defaultValues: {
      title: "",
      file: "",
    },
  });

  return form;
}
