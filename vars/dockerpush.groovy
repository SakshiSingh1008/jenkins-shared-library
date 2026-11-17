def call(String credentialsId, String image, String tag) {
    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {
        sh """
            echo "\$DOCKER_PASS" | docker login -u "\$DOCKER_USER" --password-stdin
            docker tag ${image}:${tag} \$DOCKER_USER/${image}:${tag}
            docker push \$DOCKER_USER/${image}:${tag}
            docker logout
        """
    }
}
