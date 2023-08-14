package com.thekingoftime.game.domains.game.map.monsters.usescases;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.thekingoftime.game.domains.game.entities.AnimationEntity;
import com.thekingoftime.game.domains.game.map.monsters.MonsterActor;
import com.thekingoftime.game.domains.game.shared.Shared;

public class MovimentUseCase {

    public enum Direction {
        UP, RIGHT, DOWN, LEFT, NONE
    }
    private float speed = 100f; // Velocidade do monstro
    private Rectangle patrolArea; // Área que o monstro patrulha
    private Vector2 targetPosition; // Posição-alvo do monstro
    public Direction currentDirection;
    public MonsterActor monsterActor;
    private static final float VIEW_DISTANCE = 200;
    private AnimationEntity animation;

    public boolean logicRetreat = false;
    private AttackingMonsterUseCase attackingMonsterUseCase;

    public MovimentUseCase(MonsterActor monsterActor, float x, float y, AnimationEntity animation) {
        this.monsterActor = monsterActor;
        this.animation = animation;

        patrolArea = new Rectangle(x, y, 1000, 1000);
        targetPosition = new Vector2(monsterActor.getX(), monsterActor.getY()); // Inicialmente, o alvo é a posição atual

        currentDirection = Direction.NONE; // Direção inicial (parado)

        attackingMonsterUseCase = new AttackingMonsterUseCase();
    }

    public void execute(float delta) {

        if (monsterActor.playerActor == null) {
            monsterActor.playerActor = Shared.getInstance().getData().playerActor;
            updateTargetPositionIfCloseEnoughToTargetPosition();
        } else {
            if (!setTargetToPlayerIfInRange(delta)) {
                updateTargetPositionIfCloseEnoughToTargetPosition();
            }
        }

        // Mover em direção ao alvo
        Vector2 direction = new Vector2(targetPosition).sub(monsterActor.getX(), monsterActor.getY()).nor();

        if (attackingMonsterUseCase.isAttacking) {
            if (logicRetreat) {
                Vector2 directionToPlayer = new Vector2(monsterActor.playerActor.getX() - monsterActor.getX(),
                        monsterActor.playerActor.getY() - monsterActor.getY()).nor();
                Vector2 retreatDirection = directionToPlayer.scl(-1); // Inverter direção
                float retreatDistance = 10f; // Distância para recuar
                monsterActor.setPosition(monsterActor.getX() + retreatDirection.x * retreatDistance, monsterActor.getY() + retreatDirection.y * retreatDistance);
            }
        } else {
            moveDirectionTarget(delta, direction);
            setCurrentDirection(delta, direction);
        }

    }

    private boolean setTargetToPlayerIfInRange(float delta) {
        Vector2 playerPosition = new Vector2(monsterActor.playerActor.getX(), monsterActor.playerActor.getY());
        Vector2 monsterPosition = new Vector2(monsterActor.getX(), monsterActor.getY());
        float distanceToPlayer = monsterPosition.dst(playerPosition);

        if (distanceToPlayer <= VIEW_DISTANCE) {
            monsterActor.targetPlayer = true;

            attackingMonsterUseCase.execute(this, delta, distanceToPlayer);

            targetPosition.set(playerPosition);
            return true;
        }
        return false;
    }


    private void updateTargetPositionIfCloseEnoughToTargetPosition(){
        // Se chegou ao alvo, escolha um novo alvo aleatório dentro da área de patrulha
        if (targetPosition.epsilonEquals(monsterActor.getX(), monsterActor.getY(), 10f)) {
            monsterActor.targetPlayer = false;
            targetPosition.set(randomPositionWithinPatrolArea());
        }
    }

    private void moveDirectionTarget(float delta, Vector2 direction) {
        float newX = monsterActor.getX() + direction.x * speed * delta;
        float newY = monsterActor.getY() + direction.y * speed * delta;

        if (!Shared.getInstance().getData().canMove(monsterActor.getX(), monsterActor.getY(), (direction.x * speed * delta),
                (direction.y * speed * delta))) {
            targetPosition.set(randomPositionWithinPatrolArea()); // Direção aleatória
        } else {
            monsterActor.setPosition(newX, newY);
        }
    }

    private void setCurrentDirection(float delta, Vector2 direction) {
        float deltaX = direction.x * speed * delta;
        float deltaY = direction.y * speed * delta;

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            currentDirection = deltaX > 0 ? Direction.RIGHT : Direction.LEFT;
        } else {
            currentDirection = deltaY > 0 ? Direction.UP : Direction.DOWN;
        }

        if (currentDirection.equals(Direction.UP)) {
            animation.currentAnimation = animation.upAnimation;
        } else if (currentDirection.equals(Direction.DOWN)) {
            animation.currentAnimation = animation.downAnimation;
        } else if (currentDirection.equals(Direction.LEFT)) {
            animation.currentAnimation = animation.leftAnimation;
        } else {
            animation.currentAnimation = animation.rightAnimation;
        }

    }

    private Vector2 randomPositionWithinPatrolArea() {
        return new Vector2(MathUtils.random(patrolArea.x, patrolArea.x + patrolArea.width),
                MathUtils.random(patrolArea.y, patrolArea.y + patrolArea.height));
    }
}
