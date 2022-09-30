package com.example.myapplication.models

data class ECECModel(
    val COMMANDES: List<COMMANDE>,
    val DATE_GENERATION: String,
    val NOMBRE_DE_RESULTATS: Int,
    val REFERENCES: REFERENCES
)

data class COMMANDE(
    val BLOCAGE: Boolean,
    val CLIENT: CLIENT,
    val COMMENTAIRES: List<COMMENTAIRES>,
    val COMMENTAIRE_UE: String,
    val CREATION: String,
    val DERNIER_BL: String,
    val DESTINATAIRE: DESTINATAIRE,
    val LIVRAISON: LIVRAISON,
    val NUMERO: String,
    val OKSELEC: Boolean,
    val RESTE_A_LIVRER: RESTEALIVRER,
    val SAISIE_PAR: Int,
    val SUIVIE_PAR: Int,
    val TRANSPORT: TRANSPORT,
    val TYPE: String
)

data class REFERENCES(
    val CENTRALE: List<STAppel1>,
    val CODE_PAYS: List<STAppel1>,
    val DISPONIBLE_PROD: List<STAppel1>,
    val FOURNISSEUR: List<STAppel1>,
    val IDCLIENT: List<STAppel3>,
    val SAISIE_PAR: List<STAppel1>,
    val SECTEUR: List<STAppel1>,
    val SUIVIE_PAR: List<STAppel1>,
    val TRANSPORTEUR: List<STAppel2>
)

data class STAppel1(
    val CODE: String?,
    val ID: Int?,
    val LIB: String?
)

data class STAppel2(
    val CODE: String?,
    val ID: Int?,
    val LIB: String?,
    val LIB2: String?,
    val COULEUR: String?
)

data class STAppel3(
    val CODE: String?,
    val ID: Int?,
    val NOM: String?,
    val SECTEUR: String?
)

data class CLIENT(
    val BLOCAGE: Boolean,
    val CENTRALE: Int,
    val CODE: String,
    val CODE_PAYS: Int,
    val CODE_POSTAL: String,
    val IDCLIENT: Int,
    val NOM: String,
    val RUE1: String,
    val RUE2: String,
    val SECTEUR: Int,
    val VILLE: String
)

data class COMMENTAIRES(
    val ARCHIVE: Boolean,
    val COMMENTAIRE: String,
    val DATE: String,
    val HEURE: String,
    val ID: Int,
    val SERVICE: String,
    val UTILISATEUR: String
)

data class DESTINATAIRE(
    val INFO: String,
    val MAIL: String,
    val NOM: String,
    val TEL: String
)

data class LIVRAISON(
    val CODE_PAYS: Int,
    val CODE_POSTAL: String,
    val NOM: String,
    val RUE1: String,
    val RUE2: String,
    val VILLE: String
)

data class RESTEALIVRER(
//    val FRANCOS: List<FRANCOS>,
    val LIGNES: List<LIGNES>,
    val MASSE: Double,
    val VOLUME: Double
)

data class TRANSPORT(
    val CHAMP: String,
    val DATE: String,
    val DISPONIBLE_PARTIEL_INTERDIT: Boolean,
    val NB_EXPEDITIONS: Int,
    val PARTIEL_INTERDIT: Boolean,
    val TRANSPORTEUR: Int
)

data class FRANCOS(
    val CODE_ARTICLE: String,
    val COMMENTAIRE_UL: String,
    val DATE_EXPEDITION: String,
    val LIB1: String,
    val LIB2: String,
    val LIB3: String,
    val LIB_INTERNE: String,
    val LIB_QUALITE: String,
    val LIGNE: String
)

data class LIGNES(
    val CODE_ARTICLE: String,
    val COMMENTAIRES: List<COMMENTAIRESX>,
    val COMMENTAIRE_UL: String,
//    val COMPOSANTS: List<COMPOSANTS>,
    val DATE_EXPEDITION: String,
    val DEPOT: String,
    val DERNIER_CONTROLE: String,
    val DISPONIBLE: String,
    val DISPONIBLE_PROD: String,
    val GESTION: String,
    val LIB1: String,
    val LIB2: String,
    val LIB3: String,
    val LIB_INTERNE: String,
    val LIB_QUALITE: String,
    val LIGNE: String,
    val LISSAGE: Int,
    val MASSE_RESTANTE: Double,
    val NOUVELLE_DATE: String,
    val OF_REAPPRO: List<OFREAPPROX>,
    val PREPARABLE_WMS: String,
    val QTE_COMMANDEE: Int,
    val QTE_FABRIQUEE: Int,
    val QTE_PREPAREE: Int,
    val QTE_REAPPRO: Int,
    val QTE_RESTANTE: Int,
    val RETARD: String,
    val RETARD_JOURS_OUVRES: Int,
    val STOCK: Double,
    val STOCK_DISPO: Int,
    val TOP_BESOIN: Boolean,
    val TYPART: String,
    val TYPE: String,
    val VOLUME_RESTANT: Double
)

data class COMMENTAIRESX(
    val ARCHIVE: Boolean,
    val Commentaire: String,
    val Date: String,
    val Heure: String,
    val ID: Int,
    val Service: String,
    val Utilisateur: String
)

data class COMPOSANTS(
    val CODE_ARTICLE: String,
    val LIB1: String,
    val LIB2: String,
    val NUM_BESOIN: String,
    val OF_REAPPRO: List<OFREAPPROX>,
    val QTE_BESOIN_RESTANTE: Double,
    val QTE_REAPPRO: Int,
    val SOLDE_BESOIN: String,
    val STOCK: Double,
    val STOCK_DISPO: Double,
    val TYPART: String
)

data class OFREAPPROX(
    val DATE_PREVUE: String,
    val FOURNISSEUR: Int,
    val OF: String,
    val QTE_RESTANTE: Int
)