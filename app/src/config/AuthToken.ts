export class AuthToken{
    private static readonly key = "AUTH_USER_TOKEN"

    static create(token: string){
        localStorage.setItem(this.key, token)
    }

    static get(){
        const token = localStorage.getItem(this.key)

        return token
    }
}