DROP SEQUENCE message_id_seq;
DROP SEQUENCE education_id_seq;
DROP SEQUENCE post_id_seq;
DROP SEQUENCE community_id_seq;

ALTER TABLE "messages" DROP CONSTRAINT IF EXISTS "messages_fk0";

ALTER TABLE "messages" DROP CONSTRAINT IF EXISTS "messages_fk1";

ALTER TABLE "community_members" DROP CONSTRAINT IF EXISTS "community_members_fk0";

ALTER TABLE "community_members" DROP CONSTRAINT IF EXISTS "community_members_fk1";

ALTER TABLE "community_posts" DROP CONSTRAINT IF EXISTS "community_posts_fk0";

ALTER TABLE "community_posts" DROP CONSTRAINT IF EXISTS "community_posts_fk1";

ALTER TABLE "education" DROP CONSTRAINT IF EXISTS "education_fk0";

ALTER TABLE "colleagues" DROP CONSTRAINT IF EXISTS "colleagues_fk0";

ALTER TABLE "colleagues" DROP CONSTRAINT IF EXISTS "colleagues_fk1";

DROP TABLE IF EXISTS "users";

DROP TABLE IF EXISTS "messages";

DROP TABLE IF EXISTS "communities";

DROP TABLE IF EXISTS "community_members";

DROP TABLE IF EXISTS "community_posts";

DROP TABLE IF EXISTS "education";

DROP TABLE IF EXISTS "colleagues";

