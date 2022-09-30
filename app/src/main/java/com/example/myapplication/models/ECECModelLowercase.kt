package com.example.myapplication.models
//
//data class ECECModel(
//    val commandes: List<Commande>?,
//    val date_generation: String,
//    val nombre_de_resultats: Int,
//    val references: References
//)
//
//data class Commande(
//    val blocage: Boolean,
////    val client: Client,
////    val commentaire_ue: String,
////    val commentaires: List<Commentaire>,
//    val creation: String,
//    val dernier_bl: String,
////    val destinataire: Destinataire,
//    val livraison: Livraison?,
//    val numero: String,
////    val okselec: Boolean,
////    val reste_a_livrer: ResteALivrer,
//    val saisie_par: Int,
//    val suivie_par: Int,
////    val transport: Transport,
//    val type: String
//)
//
//data class References(
//    val centrale: List<STAppel1>?,
//    val code_pays: List<STAppel1>?,
//    val disponible_prod: List<STAppel1>?,
//    val fournisseur: List<STAppel1>?,
//    val idclient: List<STAppel3>?,
//    val saisie_par: List<STAppel1>?,
//    val secteur: List<STAppel1>?,
//    val suivie_par: List<STAppel1>?,
//    val transporteur: List<STAppel2>?
//)
//
//data class STAppel1(
//    val code: String?,
//    val id: Int?,
//    val lib: String?
//)
//
//data class STAppel2(
//    val code: String?,
//    val id: Int?,
//    val lib: String?,
//    val lib2: String?,
//    val couleur: String?
//)
//
//data class STAppel3(
//    val code: String?,
//    val id: Int?,
//    val nom: String?,
//    val secteur: String?
//)
//
//data class Client(
//    val blocage: Boolean,
//    val centrale: Int,
//    val code: String,
//    val code_pays: Int,
//    val code_postal: String,
//    val idclient: Int,
//    val nom: String,
//    val rue1: String,
//    val rue2: String,
//    val secteur: Int,
//    val ville: String
//)
//
//data class Commentaire(
//    val archive: Boolean,
//    val commentaire: String,
//    val date: String,
//    val heure: String,
//    val id: Int,
//    val service: String,
//    val utilisateur: String
//)
//
//data class Destinataire(
//    val info: String,
//    val mail: String,
//    val nom: String,
//    val tel: String
//)
//
//data class Livraison(
//    val code_pays: Int,
//    val code_postal: String,
//    val nom: String,
//    val rue1: String,
//    val rue2: String,
//    val ville: String
//)
//
//data class ResteALivrer(
//    val francos: List<Franco>,
//    val lignes: List<Ligne>,
//    val masse: Double,
//    val volume: Double
//)
//
//data class Transport(
//    val champ: String,
//    val date: String,
//    val disponible_partiel_interdit: Boolean,
//    val nb_expeditions: Int,
//    val partiel_interdit: Boolean,
//    val transporteur: Int
//)
//
//data class Franco(
//    val code_article: String,
//    val commentaire_ul: String,
//    val date_expedition: String,
//    val lib1: String,
//    val lib2: String,
//    val lib3: String,
//    val lib_interne: String,
//    val lib_qualite: String,
//    val ligne: String
//)
//
//data class Ligne(
//    val code_article: String,
//    val commentaire_ul: String,
//    val commentaires: List<Commentaire>,
//    val composants: List<Composant>,
//    val date_expedition: String,
//    val depot: String,
//    val dernier_controle: String,
//    val disponible: String,
//    val disponible_prod: Int,
//    val gestion: String,
//    val lib1: String,
//    val lib2: String,
//    val lib3: String,
//    val lib_interne: String,
//    val lib_qualite: String,
//    val ligne: String,
//    val lissage: Int,
//    val masse_restante: Double,
//    val nouvelle_date: String,
//    val of_reappro: List<OfReappro>,
//    val preparable_wms: String,
//    val qte_commandee: Int,
//    val qte_fabriquee: Int,
//    val qte_preparee: Int,
//    val qte_reappro: Int,
//    val qte_restante: Int,
//    val retard: String,
//    val retard_jours_ouvres: Int,
//    val stock: Double,
//    val stock_dispo: Int,
//    val top_besoin: Boolean,
//    val typart: String,
//    val type: String,
//    val volume_restant: Double
//)
//
//data class Composant(
//    val code_article: String,
//    val lib1: String,
//    val lib2: String,
//    val num_besoin: String,
//    val of_reappro: List<OfReappro>,
//    val qte_besoin_restante: Double,
//    val qte_reappro: Int,
//    val solde_besoin: String,
//    val stock: Double,
//    val stock_dispo: Double,
//    val typart: String
//)
//
//data class OfReappro(
//    val date_prevue: String,
//    val fournisseur: Int,
//    val of: String,
//    val qte_restante: Int
//)