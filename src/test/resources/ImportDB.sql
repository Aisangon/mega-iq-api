CREATE TABLE configuration (
    id integer PRIMARY KEY,
    locale character varying(2),
    name character varying(255),
    type character varying(20),
    value character varying(255)
);

INSERT INTO configuration (id, name, type, locale, value) VALUES
(1, 'domain', NULL,'EN', 'http://www.mega-iq.com'),
(2, 'domain', NULL,'DE', 'http://de.mega-iq.com'),
(3, 'domain', NULL,'RU', 'http://ru.mega-iq.com'),
(4, 'test_title', 'PRACTICE_IQ', 'EN', 'test_title_PRACTICE_IQ'),
(5, 'test_title', 'PRACTICE_IQ', 'DE', 'test_title_PRACTICE_IQ'),
(6, 'test_title', 'PRACTICE_IQ', 'RU', 'test_title_PRACTICE_IQ'),
(7, 'test_title', 'STANDARD_IQ', 'EN', 'test_title_STANDARD_IQ'),
(8, 'test_title', 'STANDARD_IQ', 'DE', 'test_title_STANDARD_IQ'),
(9, 'test_title', 'STANDARD_IQ', 'RU', 'test_title_STANDARD_IQ'),
(10, 'test_title', 'MEGA_IQ', 'EN', 'test_title_MEGA_IQ'),
(11, 'test_title', 'MEGA_IQ', 'DE', 'test_title_MEGA_IQ'),
(12, 'test_title', 'MEGA_IQ', 'RU', 'test_title_MEGA_IQ'),
(13, 'test_title', 'MATH', 'EN', 'test_title_MATH'),
(14, 'test_title', 'MATH', 'DE', 'test_title_MATH'),
(15, 'test_title', 'MATH', 'RU', 'test_title_MATH'),
(16, 'test_title', 'GRAMMAR', 'EN', 'test_title_GRAMMAR'),
(17, 'test_title', 'GRAMMAR', 'DE', 'test_title_GRAMMAR'),
(18, 'test_title', 'GRAMMAR', 'RU', 'test_title_GRAMMAR'),
(19, 'email_subject_new_user', NULL,'EN', 'email_subject_new_user'),
(20, 'email_subject_new_user', NULL,'DE', 'email_subject_new_user'),
(21, 'email_subject_new_user', NULL,'RU', 'email_subject_new_user'),
(22, 'email_subject_email_verify', NULL,'EN', 'email_subject_email_verify'),
(23, 'email_subject_email_verify', NULL,'DE', 'email_subject_email_verify'),
(24, 'email_subject_email_verify', NULL,'RU', 'email_subject_email_verify'),
(25, 'email_subject_test_result', NULL,'EN', '{test_type_title} finished successfully'),
(26, 'email_subject_test_result', NULL,'DE', '{test_type_title} finished successfully'),
(27, 'email_subject_test_result', NULL,'RU', '{test_type_title} успешно пройден'),
(28, 'email_subject_forget', NULL,'EN', 'email_subject_forget'),
(29, 'email_subject_forget', NULL,'DE', 'email_subject_forget'),
(30, 'email_subject_forget', NULL,'RU', 'email_subject_forget'),
(31, 'email_subject_direct_login', NULL,'EN', 'email_subject_direct_login'),
(32, 'email_subject_direct_login', NULL,'DE', 'email_subject_direct_login'),
(33, 'email_subject_direct_login', NULL,'RU', 'email_subject_direct_login'),
(34, 'test_title_promo', 'PRACTICE_IQ', 'EN', 'test_title_promo_PRACTICE_IQ'),
(35, 'test_title_promo', 'STANDARD_IQ', 'EN', 'test_title_promo_STANDARD_IQ'),
(36, 'test_title_promo', 'MEGA_IQ', 'EN', 'test_title_promo_MEGA_IQ'),
(37, 'test_title_promo', 'MATH', 'EN', 'test_title_promo_MATH'),
(38, 'test_title_promo', 'GRAMMAR', 'EN', 'test_title_promo_GRAMMAR'),
(39, 'description', 'PRACTICE_IQ', 'EN', 'description_PRACTICE_IQ'),
(40, 'description', 'STANDARD_IQ', 'EN', 'description_STANDARD_IQ'),
(41, 'description', 'MEGA_IQ', 'EN', 'description_MEGA_IQ'),
(42, 'description', 'MATH', 'EN', 'description_MATH'),
(43, 'description', 'GRAMMAR', 'EN', 'description_GRAMMAR'),
(44, 'test_title_promo', 'PRACTICE_IQ', 'DE', 'test_title_promo_PRACTICE_IQ'),
(45, 'test_title_promo', 'STANDARD_IQ', 'DE', 'test_title_promo_STANDARD_IQ'),
(46, 'test_title_promo', 'MEGA_IQ', 'DE', 'test_title_promo_MEGA_IQ'),
(47, 'test_title_promo', 'MATH', 'DE', 'test_title_promo_MATH'),
(48, 'test_title_promo', 'GRAMMAR', 'DE', 'test_title_promo_GRAMMAR'),
(49, 'description', 'PRACTICE_IQ', 'DE', 'description_PRACTICE_IQ'),
(50, 'description', 'STANDARD_IQ', 'DE', 'description_STANDARD_IQ'),
(51, 'description', 'MEGA_IQ', 'DE', 'description_MEGA_IQ'),
(52, 'description', 'MATH', 'DE', 'description_MATH'),
(53, 'description', 'GRAMMAR', 'DE', 'description_GRAMMAR'),
(54, 'test_title_promo', 'PRACTICE_IQ', 'RU', 'test_title_promo_PRACTICE_IQ'),
(55, 'test_title_promo', 'STANDARD_IQ', 'RU', 'test_title_promo_STANDARD_IQ'),
(56, 'test_title_promo', 'MEGA_IQ', 'RU', 'test_title_promo_MEGA_IQ'),
(57, 'test_title_promo', 'MATH', 'RU', 'test_title_promo_MATH'),
(58, 'test_title_promo', 'GRAMMAR', 'RU', 'test_title_promo_GRAMMAR'),
(59, 'description', 'PRACTICE_IQ', 'RU', 'description_PRACTICE_IQ'),
(60, 'description', 'STANDARD_IQ', 'RU', 'description_STANDARD_IQ'),
(61, 'description', 'MEGA_IQ', 'RU', 'description_MEGA_IQ'),
(62, 'description', 'MATH', 'RU', 'description_MATH'),
(63, 'description', 'GRAMMAR', 'RU', 'description_GRAMMAR'),
(64, 'test_url', 'PRACTICE_IQ', NULL,'/iqtest/iq-practice'),
(65, 'test_url', 'STANDARD_IQ', NULL,'/iqtest/iq-standard'),
(66, 'test_url', 'MEGA_IQ', NULL,'/iqtest/mega-iq'),
(67, 'test_url', 'MATH', NULL,'/iqtest/math'),
(68, 'test_url', 'GRAMMAR', NULL,'/iqtest/grammar'),
(69, 'test_pic', 'PRACTICE_IQ', NULL,'https://storage.googleapis.com/mega-iq/iqtest/bg-big-practice.jpg'),
(70, 'test_pic', 'STANDARD_IQ', NULL,'https://storage.googleapis.com/mega-iq/iqtest/bg-big-standard.jpg'),
(71, 'test_pic', 'MEGA_IQ', NULL,'https://storage.googleapis.com/mega-iq/iqtest/bg-big-mega.jpg'),
(72, 'test_pic', 'MATH', NULL,'https://storage.googleapis.com/mega-iq/iqtest/bg-big-math.jpg'),
(73, 'test_pic', 'GRAMMAR', NULL,'https://storage.googleapis.com/mega-iq/iqtest/bg-big-grammar.jpg'),
(74, 'test_time', 'PRACTICE_IQ', NULL,'5'),
(75, 'test_time', 'STANDARD_IQ', NULL,'10'),
(76, 'test_time', 'MEGA_IQ', NULL,'30'),
(77, 'test_time', 'MATH', NULL,'10'),
(78, 'test_time', 'GRAMMAR', NULL,'10'),
(79, 'test_questions', 'PRACTICE_IQ', NULL,'4'),
(80, 'test_questions', 'STANDARD_IQ', NULL,'12'),
(81, 'test_questions', 'MEGA_IQ', NULL,'36'),
(82, 'test_questions', 'MATH', NULL,'12'),
(83, 'test_questions', 'GRAMMAR', NULL,'12'),
(84, 'message_start_test_fail', NULL,'EN', 'message_start_test_fail_EN'),
(85, 'message_delete_success', NULL,'EN', 'message_delete_success_EN'),
(86, 'message_internal_error', NULL,'EN', 'message_internal_error_EN'),
(87, 'message_invalid_access', NULL,'EN', 'message_invalid_access_EN'),
(88, 'message_wrong_request', NULL,'EN', 'message_wrong_request_EN'),
(89, 'message_start_test_fail', NULL,'RU', 'message_start_test_fail_RU'),
(90, 'message_delete_success', NULL,'RU', 'message_delete_success_RU'),
(91, 'message_internal_error', NULL,'RU', 'message_internal_error_RU'),
(92, 'message_invalid_access', NULL,'RU', 'message_invalid_access_RU'),
(93, 'message_wrong_request', NULL,'RU', 'message_wrong_request_RU'),
(94, 'message_start_test_fail', NULL,'DE', 'message_start_test_fail_DE'),
(95, 'message_delete_success', NULL,'DE', 'message_delete_success_DE'),
(96, 'message_internal_error', NULL,'DE', 'message_internal_error_DE'),
(97, 'message_invalid_access', NULL,'DE', 'message_invalid_access_DE'),
(98, 'message_wrong_request', NULL,'DE', 'message_wrong_request_DE'),
(99, 'test_expire', 'PRACTICE_IQ', NULL,'60'),
(100, 'test_expire', 'STANDARD_IQ', NULL,'60'),
(101, 'test_expire', 'MEGA_IQ', NULL,'60'),
(102, 'test_expire', 'MATH', NULL,'60'),
(103, 'test_expire', 'GRAMMAR', NULL,'60'),
(104, 'message_registration_failed', NULL,'EN', 'message_registration_failed_EN'),
(105, 'message_login_failed', NULL,'EN', 'message_login_failed_EN'),
(106, 'message_user_not_found', NULL,'EN', 'message_user_not_found_EN'),
(107, 'message_verify_email_send', NULL,'EN', 'message_verify_email_send_EN'),
(108, 'message_verify_success', NULL,'EN', 'message_verify_success_EN'),
(109, 'message_email_already_used', NULL,'EN', 'Email ''%s'' already exists'),
(110, 'message_email_forget_was_sent', NULL,'EN', 'message_email_forget_was_sent_EN'),
(112, 'message_registration_failed', NULL,'RU', 'message_registration_failed_RU'),
(113, 'message_login_failed', NULL,'RU', 'message_login_failed_RU'),
(114, 'message_user_not_found', NULL,'RU', 'message_user_not_found_RU'),
(115, 'message_verify_email_send', NULL,'RU', 'message_verify_email_send_RU'),
(116, 'message_verify_success', NULL,'RU', 'message_verify_success_RU'),
(117, 'message_email_already_used', NULL,'RU', 'Электронная почта ''%s'' уже существует'),
(118, 'message_email_forget_was_sent', NULL,'RU', 'message_email_forget_was_sent_RU'),
(120, 'message_registration_failed', NULL,'DE', 'message_registration_failed_DE'),
(121, 'message_login_failed', NULL,'DE', 'message_login_failed_DE'),
(122, 'message_user_not_found', NULL,'DE', 'message_user_not_found_DE'),
(123, 'message_verify_email_send', NULL,'DE', 'message_verify_email_send_DE'),
(124, 'message_verify_success', NULL,'DE', 'message_verify_success_DE'),
(125, 'message_email_already_used', NULL,'DE', 'Email ''%s'' already exists'),
(126, 'message_email_forget_was_sent', NULL,'DE', 'message_email_forget_was_sent_DE');