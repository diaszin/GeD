import { Auth } from "@/api/Auth";
import { Button } from "@/components/ui/button";
import { CardContent, Card } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@radix-ui/react-label";
import type { FormEvent } from "react";


function signIn(ev: FormEvent<HTMLFormElement>){
  ev.preventDefault()
  Auth.signIn("kaiquedias2013@gmail.com", "bahia1234")
}

export default function LoginPage() {
  return <div className="w-screen h-screen flex items-center justify-center min-h-screen">
      <div className="flex flex-1 flex-col justify-center px-4 py-10 lg:px-6">
        <div className="sm:mx-auto sm:w-full sm:max-w-md">
          <h3 className="mt-2 text-center text-lg font-bold text-foreground dark:text-foreground">
            Crie sua conta e coloque tudo em ordem hoje.
          </h3>
        </div>

        <Card className="mt-4 sm:mx-auto sm:w-full sm:max-w-md bg-gray-600 rounded-md bg-clip-padding backdrop-filter backdrop-blur-sm bg-opacity-20 border border-gray-100">
          <CardContent>
            <form onSubmit={signIn} className="space-y-4">

              <div>
                <Label
                  htmlFor="email-login-05"
                  className="text-sm font-medium text-foreground dark:text-foreground"
                >
                  Digite seu e-mail profissional
                </Label>
                <Input
                  type="email"
                  id="email"
                  name="email"
                  autoComplete="email"
                  placeholder="Exemplo: gestaodedocumentos@ged.br"
                  className="mt-2"
                />
              </div>

              <div>
                <Label
                  htmlFor="password-login-05"
                  className="text-sm font-medium text-foreground dark:text-foreground"
                >
                  Digite sua senha
                </Label>
                <Input
                  type="password"
                  id="password"
                  name="password"
                  autoComplete="password" 
                  className="mt-2"
                />
              </div>
              <Button type="submit" className="mt-4 w-full py-2 font-medium">
                Entrar
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
}
