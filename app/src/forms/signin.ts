import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import z from "zod";

export type SignInFormType = z.infer<typeof signInFormSchema>;

export const signInFormSchema = z.object({
  email: z.email().nonempty(),
  password: z.string().nonempty(),
});

export function useSignInForm() {
  const form = useForm({
    resolver: zodResolver(signInFormSchema),
  });

  return form;
}
