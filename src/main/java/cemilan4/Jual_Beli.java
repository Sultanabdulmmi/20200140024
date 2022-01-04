/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cemilan4;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sulta
 */

@Controller
public class Jual_Beli {
    @RequestMapping("/Jual_Beli")
    public String inputanuser(HttpServletRequest data, Model sayur){
    dataproses dtproses = new dataproses();
        //getting data
        String namasayur = data.getParameter("var_namasayur");
        String hargasayur = data.getParameter("var_hargakilo");        
        String jumlahsayur = data.getParameter("var_jumlahbeli");
        String UangCostumer = data.getParameter("var_UangCostumer");
        //import data from proccess then turn to variabel
        Double convharga        = dtproses.newharga(hargasayur);
        Double convjumlah       = dtproses.newjumlah(jumlahsayur);
        Double jumlahbayar      = dtproses.newjumlahbayar(convharga, convjumlah);
        String diskonpercent    = dtproses.diskon(jumlahbayar);
        String UangC            = dtproses.newuang(Double.valueOf(UangCostumer), jumlahbayar);
        Double hargadiskon      = dtproses.newhargadiskon(jumlahbayar, Integer.valueOf(diskonpercent));
        Double totalbayar       = dtproses.newtotalbayar(jumlahbayar, hargadiskon);
        dtproses.math(jumlahbayar, Integer.valueOf(diskonpercent), totalbayar, hargadiskon);
        //
        sayur.addAttribute("name", namasayur);
        sayur.addAttribute("price", totalbayar);
        sayur.addAttribute("kilo",jumlahsayur);
        sayur.addAttribute("totalbayar",totalbayar); 
        sayur.addAttribute("discountrp", hargadiskon);
        sayur.addAttribute("disc", diskonpercent);
        sayur.addAttribute("total0", jumlahbayar);
        sayur.addAttribute("UangCostumer",UangCostumer);
        sayur.addAttribute("Kembalian",UangC);
        return "Ami";
    }

}
