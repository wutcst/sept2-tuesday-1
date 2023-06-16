import JSEncrypt from 'jsencrypt';
const publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC78ejlQVWKh5g9m9HGmENrrhVZ\n' +
    'oagkL1xdh8+eQgLeuI4Ted8TORzWKkPhNO4Vu+MxcvmQ1ziYmdpfCqqrXSGHWtU/\n' +
    'oBfuIUiQlJgOBzQiaLZPTo0yXtjV3RNQlBuvGzXuXuPN8bVSTmf5UjNoc/j5iZ4R\n' +
    'bS0oCHHqPv4GupqaOQIDAQAB';

export  function encrypt(passwd){
    const encryptor = new JSEncrypt();
    encryptor.setPublicKey(publicKey);
    return  encryptor.encrypt(passwd)
}

