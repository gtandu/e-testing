export class User{

    mail: string;
    token: string;
    roles: string[];
    isAdmin: boolean;

    constructor(private model: any)
    {
        this.mail = model.mail;
        this.token = model.token;
    }
}