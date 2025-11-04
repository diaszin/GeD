import { Auth } from "@/api/Auth";
import { Button } from "@/components/ui/button";
import { CardContent, Card } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Spinner } from "@/components/ui/spinner";
import { AuthToken } from "@/config/AuthToken";
import { useSignInForm, type SignInFormType } from "@/forms/signin";
import { useNavigate } from "@/router";
import { Controller } from "react-hook-form";


export default function LoginPage() {
  const form = useSignInForm();
  const navigate = useNavigate();


  
  function signIn(data: SignInFormType) {
    return Auth.signIn(data.email, data.password).then((response) => {
      const token = response.data["token"];

      // Cria token de usuário e redireciona para a página principal
      AuthToken.create(token);
      navigate("/projects")
    });
  }


  return (
    <div className="w-screen h-screen flex items-center justify-center min-h-screen">
      <div className="flex flex-1 flex-col justify-center px-4 py-10 lg:px-6">
        <div className="sm:mx-auto sm:w-full sm:max-w-md">
          <h3 className="mt-2 text-center text-lg font-bold text-foreground dark:text-foreground">
            Seus arquivos. Seus dados. Tudo em um só lugar.
          </h3>
        </div>

        <Card className="mt-4 sm:mx-auto sm:w-full sm:max-w-md bg-gray-600 rounded-md bg-clip-padding backdrop-filter backdrop-blur-sm bg-opacity-20 border border-gray-100">
          <CardContent>
            <form onSubmit={form.handleSubmit(signIn)} className="space-y-4">
              <Controller
                control={form.control}
                name="email"
                render={({ field }) => (
                  <>
                    <Label
                      htmlFor="email-login-05"
                      className="text-sm font-medium text-foreground dark:text-foreground"
                    >
                      Digite seu e-mail profissional
                    </Label>
                    <Input
                      {...field}
                      type="email"
                      id="email"
                      name="email"
                      autoComplete="email"
                      placeholder="Exemplo: gestaodedocumentos@ged.br"
                      className="mt-2"
                    />
                  </>
                )}
              />

              <Controller
                control={form.control}
                name="password"
                render={({ field }) => (
                  <>
                    <Label
                      htmlFor="password-login-05"
                      className="text-sm font-medium text-foreground dark:text-foreground"
                    >
                      Digite sua senha
                    </Label>
                    <Input
                      {...field}
                      type="password"
                      id="password"
                      name="password"
                      autoComplete="password"
                      className="mt-2"
                    />
                  </>
                )}
              />
              <Button
                disabled={!form.formState.isValid}
                type="submit"
                className="mt-4 w-full py-2 font-medium"
              >
                {form.formState.isSubmitting ? <Spinner/> : "Entrar"}
              </Button>
            </form>
          </CardContent>
        </Card>

        <p className="mt-6 text-center text-sm text-muted-foreground dark:text-muted-foreground">
          Não tenho conta!{" "}
          <a
            href="/signup"
            className="font-medium text-primary hover:text-primary/90 dark:text-primary hover:dark:text-primary/90"
          >
            Quero me cadastrar
          </a>
        </p>
      </div>
    </div>
  );
}
