Inférence de type
-----------------

Quand on utilise les génériques, le compilateur se charge de déterminer le type du
paramètre générique à l'aide du type de la variable d'affectation.
Par exemple, il est possible de remplacer

    List<Candidat> resultat1 = new ArrayList<Candidat>();

Par

    List<Candidat> resultat2 = new ArrayList<>();

Trier avec un prédicat
----------------------

Avant, pour trier, on devait créer une classe implémentant une interface de comparaison:

    static class CandidatComparator implements Comparator<Candidat> {
      @Override
      public int compare(Candidat p1, Candidat p2) {
        return p2.getVotes() - p1.getVotes();
      }
    }
    
    /**
     * Retourne l'ordre d'arrivée
     */
    public List<Candidat> podium() {
      List<Candidat> resultat = new ArrayList<Candidat>(candidats);
      Collections.sort(resultat, new CandidatComparator());
      return resultat;
    }

Depuis java 6 (ou 7), il est possible d'utiliser des classes anonymes:

    List<Candidat> resultat = new ArrayList<Candidat>(candidats);
    Collections.sort(resultat, new Comparator<Candidat>() {
      @Override
      public int compare(Candidat p1, Candidat p2) {
        return p2.getVotes() - p1.getVotes();
      }
    });		
    return resultat;

Trier avec un stream
--------------------

Java 8 va plus loin et introduit la notion d'expression lambda et de stream, le code précédent
devient:

    return candidats.stream().sorted((p1, p2) -> p2.getVotes() - p1.getVotes())
      .collect(Collectors.toList());

Un Stream est un nouveau type, il représente une séquence d'éléments (supporte le travail en
parallèle). Les méthodes permettent d'effectuer des suites d'opérations, comme le filtrage
ou l'exécution. La plupart des méthodes prennent en entrée un stream et retournent un stream,
sauf les méthodes finales qui consomme le stream.

Deux méthodes pour avoir un stream():

* Stream.of()
* séquence.stream()

Autres méthodes:

* map: prend en entrée un stream, applique sur chaque élément un traitement et retourne un
nouveau stream avec le résultat des traitements
* filter: prend en entrée un stream, retourne un stream en enlevant les éléments ne répondant
pas au test
* forEach: opération terminal, prend en entrée un stream et applique une action sur chaque
élément

Que peut-on faire avec ?
------------------------

Retourner le permier élément d'une liste

    Arrays.asList("a1", "a2", "a3").stream().findFirst().ifPresent(System.out::println);
    Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println);

Appliquer une transformation et retrouner la moyenne

    Arrays.stream(new int[] {1, 2, 3}).map(n -> 2 * n + 1).average()
      .ifPresent(System.out::println);

Parser une chaine et retourner le max

    Stream.of("a1", "a2", "a3").map(s -> s.substring(1))
      .mapToInt(Integer::parseInt).max().ifPresent(System.out::println);
		
On constate qu'une operation terminal est nécessaire pour exécuter le code

    Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
      System.out.println("filter: " + s);
      return true;
    });
		
On constate qu'une operation terminal est nécessaire pour exécuter le code

    Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
      System.out.println("filter: " + s);
      return true;
    }).forEach(s -> System.out.println("forEach: " + s));
	
Evaluation du stream
--------------------

Le stream est evalué une fois

    Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c")
      .filter(s -> s.startsWith("a"));
    stream.anyMatch(s -> true);    // ok
    stream.noneMatch(s -> true);   // exception

Utiliser un Supplier pour redonner le même stream

    Supplier<Stream<String>> streamSupplier = 
      () -> Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> s.startsWith("a"));
    streamSupplier.get().anyMatch(s -> true);   // ok
    streamSupplier.get().noneMatch(s -> true);  // ok

Collecter les résultats
-----------------------

Soit la classe suivante

    static class Person {
      String name;
      int age;

      Person(String name, int age) {
        this.name = name;
        this.age = age;
      }

      @Override
      public String toString() {
        return name;
      }
    }

Et la liste de données suivante:

    List<Person> persons = Arrays.asList(
      new Person("Max", 18),
      new Person("Peter", 23),
      new Person("Pamela", 23),
      new Person("David", 12));

Collecter vers une liste de strings

    List<Person> filtered = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());
    System.out.println(filtered);    // [Peter, Pamela]

Grouper par age

    Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));
    personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

Récupérer l'age moyen

    Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));
    System.out.println(averageAge);

