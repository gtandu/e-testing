export class Paths {
  public static readonly GET_TOKEN = '/api-token';
  public static readonly QCM = '/qcm';
  public static readonly XML = '/xml';
  public static readonly QCM_XML = Paths.QCM + '/xml';

  public static readonly QUESTION_REPONSE = '/questionReponse';
  public static readonly REPONSE = '/reponse';
  public static readonly GET_USER_ROLES = '/account/{mail}/roles';
}
