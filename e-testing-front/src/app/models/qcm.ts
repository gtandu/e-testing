import { QuestionReponse } from './questionreponse';

export class Qcm {
  id: number;
  nom: String;
  listeQuestionsReponses: QuestionReponse[];
  noteFinale: number;
  totalPts: number;
}
