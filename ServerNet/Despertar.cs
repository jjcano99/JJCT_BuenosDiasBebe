using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RegalaBien.Clases
{
    public class Despertar
    {
        private string _Cita;
        public string Cita { get { return _Cita; } set { _Cita = value; } }

        private string _Foto;
        public string Foto { get { return _Foto; } set { _Foto = value; } }

        private string _Melodia;
        public string Melodia { get { return _Melodia; } set { _Melodia = value; } }

        //CONSTRUCTORES

        public Despertar() { }

        public Despertar(
           
            string Cita,
            string Foto,
            string Melodia
           )
        {     
            _Cita = Cita;
            _Foto = Foto;
            _Melodia = Melodia;
         
        }




    }
}