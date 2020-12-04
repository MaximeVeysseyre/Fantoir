SELECT n.numero, v.nom_voie, cp.code_postal, c.nom_commune 
FROM commune c 
INNER JOIN code_postal cp ON c.id_commune = cp.commune_id
INNER JOIN code_postal_voie cpv ON cp.id_code_postal = cpv.code_postal_id
INNER JOIN voie v ON cpv.voie_id = v.id_voie
INNER JOIN voie_numero vn ON v.id_voie = vn.voie_id
INNER JOIN numero n ON vn.numero_id = n.id_numero