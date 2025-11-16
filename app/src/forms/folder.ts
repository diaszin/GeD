import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import z from "zod";

export type FolderCreateFormType = z.infer<typeof folderCreateFormSchema>;
export type FolderUpdateFormType = z.infer<typeof folderUpdateFormSchema>;

export const folderCreateFormSchema = z.object({
  title: z.string().nonempty(),
});

export const folderUpdateFormSchema = z.object({
  title: z.string().nonempty(),
});

export function useFolderCreateForm() {
  const form = useForm({
    resolver: zodResolver(folderCreateFormSchema),
    defaultValues: {
      title: "",
    },
  });

  return form;
}

export function useFolderUpdateForm() {
  const form = useForm({
    resolver: zodResolver(folderUpdateFormSchema),
    defaultValues: {
      title: "",
    },
  });

  return form;
}
