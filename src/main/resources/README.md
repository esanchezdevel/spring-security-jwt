<h1>Spring Security Course Notes:</h1>

<h2>JWT</h2>
<h3>Create Keystore file</h3>
<p>For create the keystore file we can use this command:<br><br>
<b><i>keytool -genkeypair -alias myJwtKeyStore -keyalg RSA -keypass qwertyui -keystore myJwtKeyStore.jks -storepass qwertyui</i></b><br><br>
and then copy it in src/main/resources
</p>

<h3>Get Public KEY from the JKS</h3>
<p>
For get the public key we execute the following command:<br><br>
<b><i>keytool -list -rfc --keystore myJwtKeyStore.jks | openssl x509 -inform pem -pubkey</i></b><br><br>
we will get something like this:<br><br>
-----BEGIN PUBLIC KEY-----<br>
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiPV6YGbwWKO029albIl5<br>
4WakVwd1qSq3o7tyoMn+5Gb2aXGNupjzLXk20c3dOWNmx4EedXl1XlpQ3eBvTvR0<br>
zSwrzQCJ9/Z3aDcWIkIo6m/Ji7vexybFhri+K+h0wo4uP8cYlAVhBncPwSMOfLdK<br>
W6wEl5ZwenGdDqK+rCKJ15JjjmTyo6lJNTaO8I53BPbmwjp0ymtlbUNuUG7mM65G<br>
ty6Y2QAkX+l8ByQXzw5lj11KrGcG7Mh4VfFzbKNHJPPJNcKQVKOt0J9uD/x6je9q<br>
6W6DJISNtL+QBkW77D0iwejWrljCsTXjBGssqDn8MmMRKw3B94Sq8a659VO7zxQ/<br>
pwIDAQAB<br>
-----END PUBLIC KEY-----<br>
-----BEGIN CERTIFICATE-----<br>
MIIDqzCCApOgAwIBAgIELVur8zANBgkqhkiG9w0BAQsFADCBhTELMAkGA1UEBhMC<br>
RVMxDjAMBgNVBAgTBVNwYWluMQ8wDQYDVQQHEwZNYWRyaWQxGzAZBgNVBAoTEmVz<br>
YW5jaGV6LmRldmVsLm9yZzEXMBUGA1UECxMOZXNhbmNoZXouZGV2ZWwxHzAdBgNV<br>
BAMTFkVucmlxdWUgU2FuY2hleiBKb3JkYW4wHhcNMjEwNjA1MTAzODEyWhcNMjEw<br>
OTAzMTAzODEyWjCBhTELMAkGA1UEBhMCRVMxDjAMBgNVBAgTBVNwYWluMQ8wDQYD<br>
VQQHEwZNYWRyaWQxGzAZBgNVBAoTEmVzYW5jaGV6LmRldmVsLm9yZzEXMBUGA1UE<br>
CxMOZXNhbmNoZXouZGV2ZWwxHzAdBgNVBAMTFkVucmlxdWUgU2FuY2hleiBKb3Jk<br>
YW4wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCI9XpgZvBYo7Tb1qVs<br>
iXnhZqRXB3WpKreju3Kgyf7kZvZpcY26mPMteTbRzd05Y2bHgR51eXVeWlDd4G9O<br>
9HTNLCvNAIn39ndoNxYiQijqb8mLu97HJsWGuL4r6HTCji4/xxiUBWEGdw/BIw58<br>
t0pbrASXlnB6cZ0Oor6sIonXkmOOZPKjqUk1No7wjncE9ubCOnTKa2VtQ25QbuYz<br>
rka3LpjZACRf6XwHJBfPDmWPXUqsZwbsyHhV8XNso0ck88k1wpBUo63Qn24P/HqN<br>
72rpboMkhI20v5AGRbvsPSLB6NauWMKxNeMEayyoOfwyYxErDcH3hKrxrrn1U7vP<br>
FD+nAgMBAAGjITAfMB0GA1UdDgQWBBQXntptXv/IhPwmfxgPdKchCiB17jANBgkq<br>
hkiG9w0BAQsFAAOCAQEABGIlaWjIhAruanyLO9dwhBPKr1svyFIYzS8aKE8sRyEK<br>
lAV1sWVkAAds/TdKEN1UZ3G1E6xJ6KkAOfiWgVIGwnC6PoBNArGSB7iocBbI0PQN<br>
ES5uPUAMU7CsfGnZL0HWY9pgluQd6t7eMLYVSW+bVEVk/RvOUtQqc83UqmkifDEK<br>
jTQWBkPfi3Wmze42mJA8k+hIF78pjssaU/Gn6lePNJWnH2oK9ql7utRU4yqbgqTW<br>
CoCCkOIt5kYg/BH5XhqUIvwMRqGCe63q4EnlIUs1Zaofgu4TYiu3JeIKGq8XNAwX<br>
yOy0e2dcUxJpNzJTZP4ECWcSNGl8KbsWJhkUMwlkqQ==<br>
-----END CERTIFICATE-----<br>
<br><br>
And we will be interested in the part between <b>-----BEGIN PUBLIC KEY-----</b> and <b>-----END PUBLIC KEY-----</b>
</p>