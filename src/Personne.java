import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Personne {
    
        private String nom, prenom, genre, ville;
        private int annee_naissance;
        private double salaire;

        public Personne(String nom, String prenom, String genre, String ville, int annee_naissance, double salaire) {
            this.nom = nom;
            this.prenom = prenom;
            this.genre = genre;
            this.ville = ville;
            this.annee_naissance = annee_naissance;
            this.salaire = salaire;
        }

        @Override
        public String toString() {
            return "{"+nom+" "+ prenom +" "+ genre+" "+ville+" "+ annee_naissance +" "+
                   salaire+'}';

        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public String getGenre() {
            return genre;
        }

        public String getVille() {
            return ville;
        }

        public int getAnnee_naissance() {
            return annee_naissance;
        }

        public double getSalaire() {
            return salaire;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public void setVille(String ville) {
            this.ville = ville;
        }

        public void setAnnee_naissance(int annee_naissance) {
            this.annee_naissance = annee_naissance;
        }

        public void setSalaire(double salaire) {
            this.salaire = salaire;
        }
    public static void main(String[] args) {

        Personne[] personnes = getlist().toArray(Personne[]::new);

        System.out.println("Nées après 1991 :");
        Stream.of(personnes).filter(pers -> pers.getAnnee_naissance() > 1991).forEach(System.out::println);
        System.out.println();
        System.out.println("Nées en 1995 :");
        Stream.of(personnes).filter(pers -> pers.getAnnee_naissance() == 1995).map(Personne::getNom).forEach(System.out::println);
        System.out.println();
        System.out.println("Nées avant 1990 :");
        AtomicInteger compteur = new AtomicInteger(0);
        Stream.of(personnes).filter(pers -> pers.getAnnee_naissance() < 1990).map(Personne::getNom).sorted().forEach(name -> {
            System.out.println(name);
            compteur.getAndIncrement();
        });



        System.out.println("Nombres de personnes  : " + compteur);

        System.out.println("Ordonné par nom et prénom :");
        Stream.of(personnes).sorted(Comparator.comparing(Personne::getNom).thenComparing(Personne::getPrenom)).forEach(System.out::println);

        System.out.println("Femmes en J :");
        Stream.of(personnes).filter(personne -> personne.getGenre().equals("F")).filter(personne -> personne.getNom().startsWith("R")).forEach(System.out::println);

        System.out.println("Hommes en 'petit h' :");
        Stream.of(personnes).forEach(personne -> {
            personne.setGenre(personne.getGenre().toLowerCase());
            if (personne.getGenre().equals("h")) System.out.println(personne);
        });

        System.out.println("Année naissance plus jeune personne");
        Stream.of(personnes).map(Personne::getAnnee_naissance).mapToInt(Integer::intValue).max().ifPresent(System.out::println);

        System.out.println("Salaire moyen à Lyon:");
        Stream.of(personnes).filter(personne -> personne.getVille().equals("Lyon")).mapToDouble(Personne::getSalaire).average().ifPresent(System.out::println);
    }

    private static List<String> getlines(String path){
        List<String> lines = new ArrayList<>();
        try (Stream<String> pers = Files.lines(Path.of(path))){
            lines = pers.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static List<Personne> getlist(){
        String filepath = "personnes.txt";
        List<Personne> personneList = new ArrayList<>();
        List<String> lines = getlines(filepath);
        for (int i =1; i< lines.size(); i++){
            String[] line = lines.get(i).split(",");
            personneList.add(new Personne(line[1].trim(),line[0].trim(),line[4].trim(),line[5].trim(),Integer.parseInt(line[2].trim()),Double.parseDouble(line[3].trim())));
        }
        return personneList;
    }
    }



