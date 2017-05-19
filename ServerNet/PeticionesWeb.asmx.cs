using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

using System.Web.Script.Services;
using System.Web.Script.Serialization;

using RegalaBien.Clases;

namespace RegalaBien
{
    /// <summary>
    /// Descripción breve de PeticionesWeb
    /// </summary>
   // [WebService(Namespace = "http://tempuri.org/")]
    [WebService(Namespace = "http://www.regalabien.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class PeticionesWeb : System.Web.Services.WebService
    {

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void HelloWorld()
        {
            this.Context.Response.ContentType = "application/json; charset=utf-8";
            this.Context.Response.Write(new JavaScriptSerializer().Serialize("Hola a todos"));
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuenosDias1()
        {
            List<Despertar> despertares = new List<Despertar>();

            despertares.Add(new Despertar("Cita1", "Foto1", "Melodia1"));
            despertares.Add(new Despertar("Cita2", "Foto2", "Melodia2"));
            despertares.Add(new Despertar("Cita3", "Foto3", "Melodia3"));
            despertares.Add(new Despertar("Cita4", "Foto4", "Melodia4"));
            despertares.Add(new Despertar("Cita5", "Foto5", "Melodia5"));

            return new JavaScriptSerializer().Serialize(despertares);
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void BuenosDias2()
        {
            List<Despertar> despertares = new List<Despertar>();

            despertares.Add(new Despertar("Cita1", "Foto1", "Melodia1"));
            despertares.Add(new Despertar("Cita2", "Foto2", "Melodia2"));
            despertares.Add(new Despertar("Cita3", "Foto3", "Melodia3"));
            despertares.Add(new Despertar("Cita4", "Foto4", "Melodia4"));
            despertares.Add(new Despertar("Cita5", "Foto5", "Melodia5"));

            this.Context.Response.ContentType = "application/json; charset=utf-8";
            this.Context.Response.Write(new JavaScriptSerializer().Serialize(despertares));


            //return new JavaScriptSerializer().Serialize(despertares);
        }




        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void BuenosDias(string historicoDespertaresJSON)
        {

            String[] listaCitasDespertar ={
            "NO VIVAS DANDO TANTAS EXPLICACIONES; tus amigos no las necesitan, tus enemigos no las creen y los estúpidos no las entienden.",
            "El ser humano debe tener dos virtudes por si acaso: Sencillez para el triunfo y valor para el fracaso.",
            "El primer paso de la ignorancia es presumir de saber.",
            "La vida es corta. Rompe las reglas, perdona rápido, besa lento, ama de verdad, ríete sin control y nunca dejes de sonreir, por más extraño que sea el motivo.",
            "NO esperes tenerlo todo para disfrutar de la vida, ya tienes la vida para disfrutar de todo."
                                          };
            String[] listaFotosDespertar = { "img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg", "img5.jpg"};

            String[] listaMelodiasDespertar = { "melo1.mp3", "melo2.mp3", "melo3.mp3", "melo4.mp3", "melo5.mp3" };

            Despertar despertarResult = new Despertar();
            Boolean estaEnHistorico = false;
            Random rnd = new Random();

            Action DespertarAleatorio = () =>
            {
                despertarResult.Cita = listaCitasDespertar[rnd.Next(0, listaCitasDespertar.Length)];
                despertarResult.Foto = listaFotosDespertar[rnd.Next(0, listaFotosDespertar.Length)];
                despertarResult.Melodia = listaMelodiasDespertar[rnd.Next(0, listaMelodiasDespertar.Length)];
            };




            if(!String.IsNullOrEmpty(historicoDespertaresJSON)){


        /*********************************************************************/
                try
                {
                    JavaScriptSerializer jss = new JavaScriptSerializer();
                    List<Despertar> historicoDespertares = jss.Deserialize<List<Despertar>>(historicoDespertaresJSON);

                    //SELECCION DE CITA
                    List<String> listaCitasPosibles = new List<String>();
                    for (int i = 0; i < listaCitasDespertar.Length; i++)
                    {
                        estaEnHistorico = false;
                        foreach (Despertar iHistoricoDespertar in historicoDespertares)
                        {
                            if (iHistoricoDespertar.Cita == listaCitasDespertar[i])
                            {
                                estaEnHistorico = true;
                                break;
                            };
                        };
                        if (!estaEnHistorico) listaCitasPosibles.Add(listaCitasDespertar[i]);
                    };
                    if (listaCitasPosibles.Count > 0)
                    {
                        //rnd = new Random();
                        despertarResult.Cita = listaCitasPosibles[rnd.Next(0, listaCitasPosibles.Count)];
                    }
                    else despertarResult.Cita = listaCitasDespertar[rnd.Next(0, listaCitasDespertar.Length)]; ;
                    //SELECCION DE FOTO
                    List<String> listaFotosPosibles = new List<String>();
                    for (int i = 0; i < listaFotosDespertar.Length; i++)
                    {
                        estaEnHistorico = false;
                        foreach (Despertar iHistoricoDespertar in historicoDespertares)
                        {
                            if (iHistoricoDespertar.Foto == listaFotosDespertar[i])
                            {
                                estaEnHistorico = true;
                                break;
                            };
                        };
                        if (!estaEnHistorico) listaFotosPosibles.Add(listaFotosDespertar[i]);
                    };
                    if (listaFotosPosibles.Count > 0)
                    {
                        //rnd = new Random();
                        despertarResult.Foto = listaFotosPosibles[rnd.Next(0, listaFotosPosibles.Count)];
                    }
                    else despertarResult.Foto = listaFotosDespertar[rnd.Next(0, listaFotosDespertar.Length)]; ;
                    //SELECCION DE MELODIA
                    List<String> listaMelodiasPosibles = new List<String>();
                    for (int i = 0; i < listaMelodiasDespertar.Length; i++)
                    {
                        estaEnHistorico = false;
                        foreach (Despertar iHistoricoDespertar in historicoDespertares)
                        {
                            if (iHistoricoDespertar.Melodia == listaMelodiasDespertar[i])
                            {
                                estaEnHistorico = true;
                                break;
                            };
                        };
                        if (!estaEnHistorico) listaMelodiasPosibles.Add(listaMelodiasDespertar[i]);
                    };
                    if (listaMelodiasPosibles.Count > 0)
                    {
                        //rnd = new Random();
                        despertarResult.Melodia = listaMelodiasPosibles[rnd.Next(0, listaMelodiasPosibles.Count)];
                    }
                    else despertarResult.Melodia = listaMelodiasDespertar[rnd.Next(0, listaMelodiasDespertar.Length)]; ;

                }
                catch (Exception)
                {
                     DespertarAleatorio();
                   // throw;
                }


}
            /*********************************************************************/

            else{ //historicoDespertaresJSON vacío
                DespertarAleatorio();
                
 /*           
                despertarResult.Cita = listaCitasDespertar[rnd.Next(0, listaCitasDespertar.Length)];
                despertarResult.Foto = listaFotosDespertar[rnd.Next(0, listaFotosDespertar.Length)];
                despertarResult.Melodia = listaMelodiasDespertar[rnd.Next(0, listaMelodiasDespertar.Length)];
*/
            };

            this.Context.Response.ContentType = "application/json; charset=utf-8";
            this.Context.Response.Write(new JavaScriptSerializer().Serialize(despertarResult));





            //return new JavaScriptSerializer().Serialize(despertarResult);

           



        }
        //FIN BuenosDias






    }
}
