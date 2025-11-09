import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import z from "zod";

export type ProjectCreateType = z.infer<typeof projectCreateFormSchema>;
export type ProjectUpdateType = z.infer<typeof projectUpdateFormSchema>;

export const projectCreateFormSchema = z.object({
  title: z.string().nonempty(),
});

export const projectUpdateFormSchema = z.object({
  title: z.string().nonempty(),
});

export function useProjectCreateForm() {
  const form = useForm({
    resolver: zodResolver(projectCreateFormSchema),
    defaultValues: {
      title: "",
    },
  });

  return form;
}

export function useProjectUpdateForm(values: ProjectUpdateType) {
  const form = useForm({
    resolver: zodResolver(projectUpdateFormSchema),
    defaultValues: values,
  });

  return form;
}
