import java.io.*;
import java.net.*;
public class Q07 {
    public static String getHtml(String endereco){
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } 

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }

      return resp;
   }
   //função que recebe uma string e um char e conta quantas vezes apareceu na string
   static int leituraChar(String html, char x){
      int tam = html.length();
      int count = 0;
      for(int i=0;i<tam;i++){
         if(html.charAt(i)==x)
            count++;
      }
      return count;
   }
   //contador de consoantes
   static int leituraConsoante(String palavra){
      int tam = palavra.length();
      int count = 0;
      for(int i=0;i<tam;i++){
         if(!((int)palavra.charAt(i)<97 || (int)palavra.charAt(i)>122 || palavra.charAt(i)=='i' || palavra.charAt(i)=='o' || palavra.charAt(i)=='u' || palavra.charAt(i)=='a' || palavra.charAt(i)=='e')){
            count++;
         }
      }
      return count;
   }
   //contador de <br>
   static int leituraBr(String html){
      int tam = html.length();
      int count = 0;
      for(int i=0;i<tam;i++){
         if(html.charAt(i)=='<')
            if(html.charAt(i+1)=='b')
               if(html.charAt(i+2)=='r')
                  if(html.charAt(i+3)=='>')
                     count++;
      }
      return count;
   }
   //contador de tables 
   static int leituraTable(String html){
      int tam = html.length();
      int count = 0;
      for(int i=0;i<tam;i++){
         if(html.charAt(i)=='<')
            if(html.charAt(i+1)=='t')
               if(html.charAt(i+2)=='a')
                  if(html.charAt(i+3)=='b')
                     if(html.charAt(i+4)=='l')
                        if(html.charAt(i+5)=='e')
                           if(html.charAt(i+6)=='>')
                              count++;
      }
      return count;
   }
   public static void main(String[] args) {
      String endereco, html,nomePagina;
      int tam;
      for(int i=0;i<10;i++){
         nomePagina = MyIO.readLine();
         tam = nomePagina.length();
         if(tam==3 && nomePagina.charAt(0)=='F' && nomePagina.charAt(1)=='I' && nomePagina.charAt(2)=='M')//Define o fim do codigo
            break;
         endereco = MyIO.readLine();
         html = getHtml(endereco);
         int x1 = leituraChar(html, '\u0061'); // Unicode para 'a'
         int x2 = leituraChar(html, '\u0065'); // Unicode para 'e'
         int x3 = leituraChar(html, '\u0069'); // Unicode para 'i'
         int x4 = leituraChar(html, '\u006F'); // Unicode para 'o'
         int x5 = leituraChar(html, '\u0075'); // Unicode para 'u'
         int x6 = leituraChar(html, '\u00E1'); // Unicode para 'á'
         int x7 = leituraChar(html, '\u00E9'); // Unicode para 'é'
         int x8 = leituraChar(html, '\u00ED'); // Unicode para 'í'
         int x9 = leituraChar(html, '\u00F3'); // Unicode para 'ó'
         int x10 = leituraChar(html, '\u00FA'); // Unicode para 'ú'
         int x11 = leituraChar(html, '\u00E0'); // Unicode para 'à'
         int x12 = leituraChar(html, '\u00E8'); // Unicode para 'è'
         int x13 = leituraChar(html, '\u00EC'); // Unicode para 'ì'
         int x14 = leituraChar(html, '\u00F2'); // Unicode para 'ò'
         int x15 = leituraChar(html, '\u00F9'); // Unicode para 'ù'
         int x16 = leituraChar(html, '\u00E3'); // Unicode para 'ã'
         int x17 = leituraChar(html, '\u00F5'); // Unicode para 'õ'
         int x18 = leituraChar(html, '\u00E2'); // Unicode para 'â'
         int x19 = leituraChar(html, '\u00EA'); // Unicode para 'ê'
         int x20 = leituraChar(html, '\u00EE'); // Unicode para 'î'
         int x21 = leituraChar(html, '\u00F4'); // Unicode para 'ô'
         int x22 = leituraChar(html, '\u00FB'); // Unicode para 'û'

         int consoante = leituraConsoante(html);
         int br = leituraBr(html);
         int table = leituraTable(html);
         MyIO.println("a(" + x1 + ") e(" + x2 + ") i(" + x3 + ") o(" + x4 + ") u(" + x5 + ") á(" + x6 + ") é(" + x7 + ") í(" + x8 + ") ó(" + x9 + ") ú(" + x10 + ") " +
      "à(" + x11 + ") è(" + x12 + ") ì(" + x13 + ") ò(" + x14 + ") ù(" + x15 + ") ã(" + x16 + ") õ(" + x17 + ") â(" + x18 + ") ê(" + x19 + ") î(" + x20 + ") ô(" + x21 + ") û(" + x22 + ") " +
      "consoante(" + (consoante) + ") " +
      "<br>("+ br + ") <table>(" + table + ") " + nomePagina);
      }
   }
}
