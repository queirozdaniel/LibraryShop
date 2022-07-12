ALTER table `person`
    ADD COLUMN `enabled` BIT(1) not null default b'1' AFTER `last_name`;