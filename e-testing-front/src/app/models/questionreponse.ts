import { Reponse } from './reponse';
export class QuestionReponse {
  id: number;
  libelleQuestion: String;
  ptsObtenues: number;
  totalPts: number;
  listeReponses: Reponse[];
}
