
export class User {
    id: number;
    nom: string;
    prenom: string;
    nomMere: string;
    nomPere: string;
    email: string;
    matricule: string;
    telephone: string;
    province: string;
    commune: string;
    dateNaissance: Date;
    dateEntree: Date;
    centreFormation: string;
    profile: string;
    sexe: string;
    statusMatrimoniale: string;


  constructor(id: number, nom: string, prenom: string, nomMere: string, nomPere: string, email: string,
              matricule: string, telephone: string, province: string, commune:string, dateNaissance: Date,
              dateEntree: Date, centreFormation: string, profile: string, sexe: string, statusMatrimoniale: string) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.nomMere = nomMere;
    this.nomPere = nomPere;
    this.email = email;
    this.matricule = matricule;
    this.telephone = telephone;
    this.province = province;
    this.commune = commune;
    this.dateNaissance = dateNaissance;
    this.dateEntree = dateEntree;
    this.centreFormation = centreFormation;
    this.profile = profile;
    this.sexe = sexe;
    this.statusMatrimoniale = statusMatrimoniale;
  }
}
