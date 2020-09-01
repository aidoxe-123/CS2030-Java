import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class Case {
   int caseID;
}

class ImportedCase extends Case {
   String country;
}

class LocalCase extends Case {
}

class Cluster {
   String name;
   List<Case> caseList;
}

class Contact {
   String nature;
   Case rootCase;
   Case infectedCase;
}

class ContactTracking {
   HashMap<Case, List<Contact>> caseListTracking;
}